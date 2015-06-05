package com.jive.myco.jazz.api.health;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.jive.myco.commons.concurrent.Pnky;
import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.hawtdispatch.DefaultDispatchQueueBuilder;
import com.jive.myco.commons.hawtdispatch.DispatchQueueBuilder;
import com.jive.myco.commons.lifecycle.ListenableLifecycled;

/**
 * A basic health check base class that provides on-demand status calculations. Subclasses should
 * implement {@link #calculateHealthStatusSync()}, or {@link #calculateHealthStatusAndMessageSync()}
 * to return the current status on-demand.
 *
 * For purely async / non-blocking health check implementations, consider extending
 * {@link AbstractAsyncPeriodicHealthCheck} directly.
 *
 * @author David Valeri
 */
@Slf4j
public abstract class PeriodicHealthCheck extends AbstractAsyncPeriodicHealthCheck implements
    ListenableLifecycled
{
  /**
   * A thread pool used for blocking background processes.
   */
  private volatile Executor executor;

  /**
   * A flag indicating if this instance created its own executor internally and should therefore
   * destroy the executor when shutting down.
   */
  private volatile boolean destroyExecutor;

  public PeriodicHealthCheck(final String id, final long checkInterval)
  {
    this(id, checkInterval, DefaultDispatchQueueBuilder.getDefaultBuilder());
  }

  public PeriodicHealthCheck(
      final String id,
      final long period,
      final DispatchQueueBuilder dispatchQueueBuilder)
  {
    this(id, period, dispatchQueueBuilder, null);
  }

  public PeriodicHealthCheck(
      final String id,
      final long period,
      @NonNull final DispatchQueueBuilder dispatchQueueBuilder,
      final Executor executor)
  {
    this(
        id,
        period,
        TimeUnit.MILLISECONDS,
        DEFAULT_LIFECYCLE_GRACE_PERIOD,
        TimeUnit.MILLISECONDS,
        dispatchQueueBuilder,
        null,
        null,
        executor);
  }

  public PeriodicHealthCheck(
      final String id,
      final long period,
      @NonNull final TimeUnit periodUnit,
      final long lifecycleGracePeriod,
      @NonNull final TimeUnit lifecycleGracePeriodUnit,
      final DispatchQueueBuilder dispatchQueueBuilder,
      final Supplier<HealthStatusAndMessage> unstartedHealthStatusAndMessageSupplier,
      final Supplier<HealthStatusAndMessage> lifecycleGracePeriodHealthStatusAndMessageSupplier,
      final Executor executor)
  {
    super(
        id,
        period,
        periodUnit,
        lifecycleGracePeriod,
        lifecycleGracePeriodUnit,
        dispatchQueueBuilder,
        unstartedHealthStatusAndMessageSupplier,
        lifecycleGracePeriodHealthStatusAndMessageSupplier);

    this.executor = executor;
  }

  /**
   * Returns the executor in use for this check.
   */
  protected final Executor getExecutor()
  {
    return executor;
  }

  /**
   * Performs additional initialization actions specific to sub-classes. Subclasses overriding this
   * method MUST call the this super method.
   */
  @Override
  protected PnkyPromise<Void> doInit()
  {
    if (executor == null)
    {
      destroyExecutor = true;

      executor = Executors.newCachedThreadPool(
          new ThreadFactoryBuilder()
              .setDaemon(true)
              .setNameFormat("periodic-health-check-" + getId() + "-%d")
              .setUncaughtExceptionHandler(new UncaughtExceptionHandler()
              {
                @Override
                public void uncaughtException(final Thread t, final Throwable e)
                {
                  log.error("[{}]: Unhandled exception in check.", getId(), e);
                }
              })
              .build());
    }

    return Pnky.immediatelyComplete(null);
  }

  /**
   * Performs additional cleanup actions specific to sub-classes. Subclasses overriding this method
   * MUST call the this super method.
   */
  @Override
  protected PnkyPromise<Void> doDestroy()
  {
    if (executor != null && destroyExecutor)
    {
      ((ExecutorService) executor).shutdownNow();
    }

    executor = null;

    return Pnky.immediatelyComplete(null);
  }

  /**
   * Called from {@link #calculateHealthStatus()} to calculate a new value for this check's current
   * status. This method is invoked on {@link #executor} and may therefore perform blocking
   * operations should {@link #executor} allow such operations. To calculate the new status
   * asynchronously, override {@link #calculateHealthStatus()} instead of this method.
   *
   * @return the new status for the check
   */
  protected HealthStatus calculateHealthStatusSync()
  {
    return HealthStatus.UNKNOWN;
  }

  /**
   * Called from {@link #updateHealthStatus()} on the lifecycle queue to calculate a new value for
   * this check's current status. Implementations MUST NOT perform blocking operations in this
   * method. Delegate to {@link #executor} for blocking background operations or implement this
   * method directly for an asynchronous, non-blocking check calculation.
   *
   * @return the new status for the check
   *
   * @deprecated extend {@link AbstractAsyncPeriodicHealthCheck} directly and override
   *             {@link AbstractAsyncPeriodicHealthCheck#calculateHealthStatusAndMessage()} instead.
   */
  @Deprecated
  protected PnkyPromise<HealthStatus> calculateHealthStatus()
  {
    return Pnky.supplyAsync(this::calculateHealthStatusSync, executor);
  }

  /**
   * Called from {@link #calculateHealthStatusAndMessage()} to calculate a new value for this
   * check's current status. This method is invoked on {@link #executor} and may therefore perform
   * blocking operations should {@link #executor} allow such operations. To calculate the new status
   * asynchronously, override {@link #calculateHealthStatusAndMessage()} instead of this method. The
   * default implementation of this method delegates to {@link #calculateHealthStatusSync}.
   *
   * @return the new status and message for the check
   */
  protected HealthStatusAndMessage calculateHealthStatusAndMessageSync()
  {
    return new HealthStatusAndMessage(calculateHealthStatusSync(), null);
  }

  /**
   * Called from {@link #updateHealthStatus()} on the lifecycle queue to calculate a new value for
   * this check's current status. Implementations MUST NOT perform blocking operations in this
   * method. Delegate to {@link #executor} for blocking background operations or implement this
   * method directly for an asynchronous, non-blocking check calculation. The default implementation
   * delegates to {@link #calculateHealthStatusAndMessageSync()} on the {@link #executor}.
   *
   * @return the new status and message for the check
   *
   * @see #calculateHealthStatusAndMessageSync()
   *
   * @deprecated extend {@link AbstractAsyncPeriodicHealthCheck} directly and override
   *             {@link AbstractAsyncPeriodicHealthCheck#calculateHealthStatusAndMessage()} instead.
   */
  @Deprecated
  @Override
  protected PnkyPromise<HealthStatusAndMessage> calculateHealthStatusAndMessage()
  {
    return Pnky.supplyAsync(this::calculateHealthStatusAndMessageSync, executor);
  }

  protected static abstract class PeriodicHealthCheckBuilder<T extends AbstractAsyncPeriodicHealthCheckBuilder<T>>
      extends AbstractAsyncPeriodicHealthCheckBuilder<T>
  {
    protected Executor executor;

    protected T executor(final Executor executor)
    {
      this.executor = executor;
      return getThis();
    }
  }
}

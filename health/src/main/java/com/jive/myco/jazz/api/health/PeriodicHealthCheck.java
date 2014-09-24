package com.jive.myco.jazz.api.health;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import org.fusesource.hawtdispatch.DispatchQueue;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.jive.myco.commons.concurrent.Pnky;
import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.hawtdispatch.DefaultDispatchQueueBuilder;
import com.jive.myco.commons.hawtdispatch.DispatchQueueBuilder;
import com.jive.myco.commons.lifecycle.AbstractLifecycled;
import com.jive.myco.commons.lifecycle.LifecycleStage;
import com.jive.myco.commons.lifecycle.Lifecycled;

/**
 * A basic health check base class that provides on-demand status calculations. Subclasses should
 * implement {@link #calculateHealthStatus()} to return the current status on-demand.
 *
 * @author David Valeri
 */
@Slf4j
public abstract class PeriodicHealthCheck extends AbstractHealthCheck implements Lifecycled
{
  /**
   * The helper used to skirt single inheritance.
   */
  private final LifecycledHelper lifecycledHelper;

  /**
   * The interval between executions of the health check. If the execution takes longer than this
   * interval, the next execution of the check occurs immediately.
   */
  private final long checkInterval;

  private final DispatchQueueBuilder dispatchQueueBuilder;

  /**
   * A thread pool used for blocking background processes.
   */
  private volatile Executor executor;

  /**
   * A flag indicating if this instance created its own executor internally and should therefore
   * destroy the executor when shutting down.
   */
  private volatile boolean destroyExecutor;

  /**
   * The time when we next expect to to fork a new process to perform a check.
   */
  private volatile long nextCheckTime = 0;

  public PeriodicHealthCheck(final String id, final long checkInterval)
  {
    this(id, checkInterval, DefaultDispatchQueueBuilder.getDefaultBuilder());
  }

  public PeriodicHealthCheck(final String id, final long checkInterval,
      final DispatchQueueBuilder dispatchQueueBuilder)
  {
    this(id, checkInterval, dispatchQueueBuilder, null);
  }

  public PeriodicHealthCheck(final String id, final long checkInterval,
      @NonNull final DispatchQueueBuilder dispatchQueueBuilder,
      final Executor executor)
  {
    super(id);

    Preconditions.checkArgument(checkInterval > 0, "checkInterval must be greater than 0.");

    this.checkInterval = checkInterval;
    this.dispatchQueueBuilder = dispatchQueueBuilder;
    this.executor = executor;

    lifecycledHelper = new LifecycledHelper(
        dispatchQueueBuilder.segment("check", id, "lifecycle").build());
  }

  @Override
  public final PnkyPromise<Void> init()
  {
    return lifecycledHelper.init();
  }

  @Override
  public final PnkyPromise<Void> destroy()
  {
    return lifecycledHelper.destroy();
  }

  @Override
  public final LifecycleStage getLifecycleStage()
  {
    return lifecycledHelper.getLifecycleStage();
  }

  /**
   * Returns the lifecycle queue.
   */
  protected final DispatchQueue getLifecycleQueue()
  {
    return lifecycledHelper.getLifecycleQueue();
  }

  /**
   * Returns the executor in use for this check.
   */
  protected final Executor getExecutor()
  {
    return executor;
  }

  /**
   * Performs additional initializtion actions specific to sub-classes. The default implementation
   * returns an immediately complete future.
   */
  protected PnkyPromise<Void> doInit()
  {
    return Pnky.immediatelyComplete(null);
  }

  /**
   * Performs additional cleanup actions specific to sub-classes. The default implementation returns
   * an immediately complete future.
   */
  protected PnkyPromise<Void> doDestroy()
  {
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
   * method directly for an asynchronous, non-blocking check calculcation.
   *
   * @return the new status for the check
   */
  protected PnkyPromise<HealthStatus> calculateHealthStatus()
  {
    return Pnky.supplyAsync(this::calculateHealthStatusSync, executor);
  }

  private PnkyPromise<Void> initInternal()
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

    return doInit()
        // Schedule the scheduling to happen after init finishes such that we are fully
        // initialized when the scheduling runs.
        .thenAccept((result) -> getLifecycleQueue().execute(this::scheduleCheck));
  }

  private PnkyPromise<Void> destroyInternal()
  {
    return doDestroy()
        .thenAccept((result) ->
        {
          if (executor != null && destroyExecutor)
          {
            ((ExecutorService) executor).shutdownNow();
          }

          executor = null;

          nextCheckTime = 0;

          setHealthStatus(HealthStatus.UNKNOWN);
        });
  }

  /**
   * Triggers {@link #calculateHealthStatus()} and reschedules the next execution.
   * <p>
   * This method must be invoked on the {@link #getLifecycleQueue() lifecycle queue}.
   */
  private void updateHealthStatus()
  {
    if (getLifecycleStage() == LifecycleStage.INITIALIZED)
    {
      try
      {
        calculateHealthStatus()
            // Process the check results on the lifecycle queue
            .alwaysAccept((result, cause) ->
            {
              if (getLifecycleStage() == LifecycleStage.INITIALIZED)
              {
                if (cause == null)
                {
                  setHealthStatus(result);
                }
                else
                {
                  log.error(
                      "[{}]: Health check threw exception.  Setting status to critical.",
                      getId(), cause);
                  setHealthStatus(HealthStatus.CRITICAL);
                }
              }
              else
              {
                log.debug("[{}]: Ignoring check outcome.  Lifecycle stage is [{}].", getId(),
                    lifecycledHelper.getLifecycleStage());
              }
            }, getLifecycleQueue())
            // Requeue check, using a different queue to prevent getting stuck in a loop on the
            // lifecycle queue.
            .alwaysAccept((result, cause) ->
            {
              getLifecycleQueue().execute(this::scheduleCheck);
            }, dispatchQueueBuilder.getDispatcher().getGlobalQueue());
      }
      catch (final RuntimeException e)
      {
        getLifecycleQueue().resume();
        throw e;
      }
    }
    else
    {
      log.debug("[{}]: Skipping scheduled check.  Lifecycle stage is [{}].", getId(),
          lifecycledHelper.getLifecycleStage());
    }
  }

  /**
   * Schedules the next execution of the forked process, attempting to maintain the accuracy of
   * {@link #checkInterval}.
   * <p>
   * This method must be invoked on the {@link #getLifecycleQueue() lifecycle queue}.
   */
  private void scheduleCheck()
  {
    if (getLifecycleStage() == LifecycleStage.INITIALIZED)
    {
      final long currentTime = System.currentTimeMillis();

      // We took too long so run it immediately.
      if (nextCheckTime <= currentTime)
      {
        if (nextCheckTime != 0)
        {
          log.debug(
              "[{}]: Check was expected to next run at [{}], but the current time is [{}]. "
                  + "Scheduling check immediately.",
              getId(), nextCheckTime, currentTime);
        }

        // Calculate the time when we next expect to run.
        nextCheckTime = currentTime + checkInterval;

        getLifecycleQueue().execute(this::updateHealthStatus);
      }
      // We have time left before we were next scheduled to run, figure out how long until then and
      // schedule us for that time.
      else
      {
        final long delay = Math.max(0, nextCheckTime - currentTime);

        log.debug(
            "[{}]: Next check expected to run at [{}].  Current time is [{}].  "
                + "Scheduling check with delay [{}].",
            getId(), nextCheckTime, currentTime, delay);

        getLifecycleQueue().executeAfter(
            delay,
            TimeUnit.MILLISECONDS,
            this::updateHealthStatus);

        // Calculate the time when we next expect to run.
        nextCheckTime += checkInterval;
      }
    }
    else
    {
      log.debug("[{}]: Not rescheduling.  Lifecycle stage is [{}].", getId(),
          lifecycledHelper.getLifecycleStage());
    }
  }

  /**
   * A little helper to mix-in so we don't have to re-implement all of {@link AbstractLifecycled}.
   */
  private final class LifecycledHelper extends AbstractLifecycled
  {
    public LifecycledHelper(final DispatchQueue lifecycleQueue)
    {
      super(lifecycleQueue);
    }

    @Override
    protected PnkyPromise<Void> initInternal()
    {
      return PeriodicHealthCheck.this.initInternal();
    }

    @Override
    protected PnkyPromise<Void> destroyInternal()
    {
      return PeriodicHealthCheck.this.destroyInternal();
    }

    private DispatchQueue getLifecycleQueue()
    {
      return lifecycleQueue;
    }
  }
}

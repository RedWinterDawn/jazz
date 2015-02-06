package com.jive.myco.jazz.api.health;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import org.fusesource.hawtdispatch.DispatchQueue;

import com.google.common.base.Preconditions;
import com.jive.myco.commons.concurrent.Pnky;
import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.hawtdispatch.DefaultDispatchQueueBuilder;
import com.jive.myco.commons.hawtdispatch.DispatchQueueBuilder;
import com.jive.myco.commons.lifecycle.AbstractLifecycled;
import com.jive.myco.commons.lifecycle.LifecycleListener;
import com.jive.myco.commons.lifecycle.LifecycleStage;
import com.jive.myco.commons.lifecycle.ListenableLifecycled;
import com.jive.myco.commons.listenable.Listenable;

/**
 * A basic health check base class that provides periodic status calculations. Subclasses should
 * implement {@link #calculateHealthStatusAndMessage()} to return the current status on-demand. The
 * calculation must be non-blocking or occur on a separate thread from that which is used to invoke
 * {@link #calculateHealthStatusAndMessage()}. For health checks that perform blocking operations in
 * a synchronous manner, see {@link PeriodicHealthCheck}.
 *
 * @author David Valeri
 */
@Slf4j
public abstract class AbstractAsyncPeriodicHealthCheck extends AbstractHealthCheck implements
    ListenableLifecycled
{
  protected static final long DEFAULT_LIFECYCLE_GRACE_PERIOD = 0L;

  /**
   * The default health status and message returned when the health check is not running.
   */
  private static final HealthStatusAndMessage DEFAULT_UNSTARTED_HEALTH_STATUS_AND_MESSAGE =
      new HealthStatusAndMessage(
          HealthStatus.UNKNOWN,
          "The health check is not running or has not yet calculated a value.");

  /**
   * The default health status and message returned when the health check is in the lifecycle grace
   * period.
   */
  private static final HealthStatusAndMessage DEFAULT_LIFECYCLE_GRACE_PERIOD_HEALTH_STATUS_AND_MESSAGE =
      new HealthStatusAndMessage(
          HealthStatus.INFO,
          "The health check is in its lifecycle grace period.");

  private static final Supplier<HealthStatusAndMessage> DEFAULT_UNSTARTED_SUPPLIER =
      () -> DEFAULT_UNSTARTED_HEALTH_STATUS_AND_MESSAGE;

  private static final Supplier<HealthStatusAndMessage> DEFAULT_LIFECYCLE_GRACE_PERIOD_SUPPLIER =
      () -> DEFAULT_LIFECYCLE_GRACE_PERIOD_HEALTH_STATUS_AND_MESSAGE;

  /**
   * The helper used to skirt single inheritance.
   */
  private final LifecycledHelper lifecycledHelper;

  /**
   * The interval, in milliseconds, between executions of the health check. If the execution takes
   * longer than this interval, the next execution of the check occurs immediately.
   */
  private final long checkInterval;

  /**
   * The period, in milliseconds, after startup during which the real health status is not reported.
   * During this period, the status returned by
   * {@link #getLifecycleGracePeriodHealthStatusAndMessage()} is reported. If set to a value less
   * than or equal to {@code 0}, the {@link #getDefaultUnstartedHealthStatusAndMessage() default}
   * value is returned until the grace period has elapsed.
   */
  private final long lifecycleGracePeriod;

  private final Supplier<HealthStatusAndMessage> unstartedHealthStatusAndMessageSupplier;

  private final Supplier<HealthStatusAndMessage> lifecycleGracePeriodHealthStatusAndMessageSupplier;

  private final DispatchQueueBuilder dispatchQueueBuilder;

  /**
   * A boolean flag indicating if the health check is in the startup grace period. This flag is used
   * to reduce the logic required to test for the grace period once the grace period has elapsed.
   */
  @Getter
  private volatile AtomicBoolean inLifecycleGracePeriod;

  /**
   * The time when we next expect to to fork a new process to perform a check.
   */
  private volatile long nextCheckTime;

  /**
   * The last calculated health status and message or the
   * {@link #getDefaultUnstartedHealthStatusAndMessage() default} health status and message if the
   * check has not yet run. This value is used to hold onto any calculated value such that the grace
   * period value may be replaced at the expiration of the grace period.
   */
  private volatile HealthStatusAndMessage lastHealthStatusAndMessage = null;

  public AbstractAsyncPeriodicHealthCheck(
      final String id,
      final long period,
      @NonNull final TimeUnit periodUnit,
      final long lifecycleGracePeriod,
      @NonNull final TimeUnit lifecycleGracePeriodUnit,
      final DispatchQueueBuilder dispatchQueueBuilder,
      final Supplier<HealthStatusAndMessage> unstartedHealthStatusAndMessageSupplier,
      final Supplier<HealthStatusAndMessage> lifecycleGracePeriodHealthStatusAndMessageSupplier)
  {
    super(id);

    Preconditions.checkArgument(period > 0, "period must be greater than 0.");

    this.checkInterval = TimeUnit.MILLISECONDS.convert(period, periodUnit);

    this.lifecycleGracePeriod =
        TimeUnit.MILLISECONDS.convert(lifecycleGracePeriod, lifecycleGracePeriodUnit);

    this.dispatchQueueBuilder = dispatchQueueBuilder == null
        ? DefaultDispatchQueueBuilder.getDefaultBuilder() : dispatchQueueBuilder;

    if (unstartedHealthStatusAndMessageSupplier == null)
    {
      this.unstartedHealthStatusAndMessageSupplier = DEFAULT_UNSTARTED_SUPPLIER;
    }
    else
    {
      this.unstartedHealthStatusAndMessageSupplier =
          unstartedHealthStatusAndMessageSupplier;
    }

    if (lifecycleGracePeriodHealthStatusAndMessageSupplier == null)
    {
      this.lifecycleGracePeriodHealthStatusAndMessageSupplier =
          DEFAULT_LIFECYCLE_GRACE_PERIOD_SUPPLIER;
    }
    else
    {
      this.lifecycleGracePeriodHealthStatusAndMessageSupplier =
          lifecycleGracePeriodHealthStatusAndMessageSupplier;
    }

    lifecycledHelper = new LifecycledHelper(
        id,
        this.dispatchQueueBuilder.segment("check", id, "lifecycle").build());
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
  public boolean isRestartable()
  {
    return false;
  }

  @Override
  public final Listenable<LifecycleListener> getLifecycleListenable()
  {
    return lifecycledHelper.getLifecycleListenable();
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
   * Performs additional initialization actions specific to sub-classes. The default implementation
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
   * Called from {@link #updateHealthStatus()} on the lifecycle queue to calculate a new value for
   * this check's current status. Implementations MUST NOT perform blocking operations in this
   * method. The default implementation returns {@link HealthStatus#UNKNOWN}.
   *
   * @return the new status and message for the check
   *
   */
  protected abstract PnkyPromise<HealthStatusAndMessage> calculateHealthStatusAndMessage();

  private PnkyPromise<Void> initInternal()
  {
    // We have a grace period, turn it on and set the startup time...
    if (lifecycleGracePeriod > 0)
    {
      inLifecycleGracePeriod = new AtomicBoolean(true);

      lastHealthStatusAndMessage = lifecycleGracePeriodHealthStatusAndMessageSupplier.get();
      setHealthStatus(lastHealthStatusAndMessage);

      // Setup a task to run when the grace period expires. The task will set the status to the
      // last calculated or default value when the grace period expires. Subsequent executions of
      // the periodic check will then report the calculated value.
      //
      // Despite the fact that all checks of state are done on the lifecycle queue, we use an atomic
      // boolean to hold the flag indicating if somebody won such that we can reset the state
      // between restarts of the health check so that an old task scheduled here doesn't clobber the
      // state of a restarted health check.
      getLifecycleQueue().executeAfter(
          lifecycleGracePeriod,
          TimeUnit.MILLISECONDS,
          () ->
          {
            if (inLifecycleGracePeriod.compareAndSet(true, false))
            {
              log.debug(
                  "[{}]: Grace period expired, updating health check to last calculated "
                      + "/ default value [{}].",
                  getId(),
                  lastHealthStatusAndMessage);

              setHealthStatus(lastHealthStatusAndMessage);
            }
            else
            {
              log.debug(
                  "[{}]: Grace period expired but check was destroyed.",
                  getId());
            }
          });
    }
    else
    {
      inLifecycleGracePeriod = new AtomicBoolean(false);

      lastHealthStatusAndMessage = unstartedHealthStatusAndMessageSupplier.get();
      setHealthStatus(lastHealthStatusAndMessage);
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
          inLifecycleGracePeriod.set(false);
          inLifecycleGracePeriod = null;
          lastHealthStatusAndMessage = null;
          nextCheckTime = 0;

          setHealthStatus(unstartedHealthStatusAndMessageSupplier.get());
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
        calculateHealthStatusAndMessage()
            // Process the check results on the lifecycle queue
            .alwaysAccept((result, cause) ->
            {
              if (getLifecycleStage() == LifecycleStage.INITIALIZED)
              {
                if (cause == null)
                {
                  setHealthStatusInternal(result);
                }
                else
                {
                  log.error(
                      "[{}]: Health check threw exception.  Setting status to critical.",
                      getId(), cause);
                  setHealthStatusInternal(new HealthStatusAndMessage(HealthStatus.CRITICAL));
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

        log.trace(
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
   * Sets the current health status and message value while accounting for the grace period, if
   * configured.
   *
   * @param healthStatusAndMessage
   *          the new health status and message to set
   */
  private void setHealthStatusInternal(final HealthStatusAndMessage healthStatusAndMessage)
  {
    lastHealthStatusAndMessage = healthStatusAndMessage;

    // If in use, a scheduled task flips this state when the grace period expires. Subsequent
    // periodic updates will then report live status.
    if (inLifecycleGracePeriod.get())
    {
      log.debug("[{}]: Suppressing health status [{}] during lifecycle grace period.",
          getId(), lastHealthStatusAndMessage);
    }
    else
    {
      setHealthStatus(healthStatusAndMessage);
    }
  }

  /**
   * A little helper to mix-in so we don't have to re-implement all of {@link AbstractLifecycled}.
   */
  private final class LifecycledHelper extends AbstractLifecycled
  {
    public LifecycledHelper(final String id, final DispatchQueue lifecycleQueue)
    {
      super(id, lifecycleQueue);
    }

    @Override
    protected PnkyPromise<Void> initInternal()
    {
      return AbstractAsyncPeriodicHealthCheck.this.initInternal();
    }

    @Override
    protected PnkyPromise<Void> destroyInternal()
    {
      return AbstractAsyncPeriodicHealthCheck.this.destroyInternal();
    }

    private DispatchQueue getLifecycleQueue()
    {
      return lifecycleQueue;
    }
  }

  protected static abstract class AbstractAsyncPeriodicHealthCheckBuilder<T extends AbstractAsyncPeriodicHealthCheckBuilder<T>>
  {
    protected String id;

    protected long period = 5000L;
    protected TimeUnit periodUnit = TimeUnit.MILLISECONDS;

    protected long lifecycleGracePeriod = 0L;
    protected TimeUnit lifecycleGracePeriodUnit = TimeUnit.MILLISECONDS;

    protected DispatchQueueBuilder dispatchQueueBuilder;

    protected Supplier<HealthStatusAndMessage> unstartedHealthStatusAndMessageSupplier;
    protected Supplier<HealthStatusAndMessage> lifecycleGracePeriodHealthStatusAndMessageSupplier;

    protected T id(final String id)
    {
      this.id = id;
      return getThis();
    }

    protected T period(final long period)
    {
      this.period = period;
      return getThis();
    }

    protected T periodUnit(final TimeUnit periodUnit)
    {
      this.periodUnit = periodUnit;
      return getThis();
    }

    protected T lifecycleGracePeriod(final long lifecycleGracePeriod)
    {
      this.lifecycleGracePeriod = lifecycleGracePeriod;
      return getThis();
    }

    protected T lifecycleGracePeriodUnit(final TimeUnit lifecycleGracePeriodUnit)
    {
      this.lifecycleGracePeriodUnit = lifecycleGracePeriodUnit;
      return getThis();
    }

    protected T dispatchQueueBuilder(final DispatchQueueBuilder dispatchQueueBuilder)
    {
      this.dispatchQueueBuilder = dispatchQueueBuilder;
      return getThis();
    }

    protected T defaultUnstartedHealthStatusAndMessageSupplier(
        final Supplier<HealthStatusAndMessage> unstartedHealthStatusAndMessageSupplier)
    {
      this.unstartedHealthStatusAndMessageSupplier = unstartedHealthStatusAndMessageSupplier;
      return getThis();
    }

    protected T lifecycleGracePeriodHealthStatusAndMessageSupplier(
        final Supplier<HealthStatusAndMessage> lifecycleGracePeriodHealthStatusAndMessageSupplier)
    {
      this.lifecycleGracePeriodHealthStatusAndMessageSupplier =
          lifecycleGracePeriodHealthStatusAndMessageSupplier;
      return getThis();
    }

    protected abstract T getThis();
  }
}

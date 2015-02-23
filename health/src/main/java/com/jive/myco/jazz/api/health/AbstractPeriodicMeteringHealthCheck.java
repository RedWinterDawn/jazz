package com.jive.myco.jazz.api.health;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.function.Supplier;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import com.codahale.metrics.Meter;
import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.hawtdispatch.DispatchQueueBuilder;

/**
 * Base class for health checks backed by a statistical meter that is periodically updated and/or
 * examined to determine health status.
 * <p>
 * The health check may be updated with events via {@link #mark()} or {@link #mark(long)} at any
 * time.
 *
 * @author David Valeri
 */
@Slf4j
public abstract class AbstractPeriodicMeteringHealthCheck extends AbstractAsyncPeriodicHealthCheck
  implements com.jive.myco.jazz.api.metrics.Meter
{
  private final Function<Meter, PnkyPromise<HealthStatusAndMessage>> healthCalculator;
  private final boolean supressUpdatesDuringLifecycleGracePeriod;
  private volatile Meter meter;

  public AbstractPeriodicMeteringHealthCheck(
      @NonNull final String id,
      final long period,
      final TimeUnit periodUnit,
      final long lifecycleGracePeriod,
      @NonNull final TimeUnit lifecycleGracePeriodUnit,
      final DispatchQueueBuilder dispatchQueueBuilder,
      final Supplier<HealthStatusAndMessage> unstartedHealthStatusAndMessageSupplier,
      final Supplier<HealthStatusAndMessage> lifecycleGracePeriodHealthStatusAndMessageSupplier,
      @NonNull final Function<Meter, PnkyPromise<HealthStatusAndMessage>> healthCalculator,
      final boolean supressUpdatesDuringLifecycleGracePeriod)
  {
    super(
        id,
        // Chop down the requested check period. The internal meter will only update its value
        // every 5 seconds
        periodUnit.convert(period, TimeUnit.SECONDS) > 5 ? periodUnit.toMillis(period)
            : TimeUnit.SECONDS.toMillis(5),
        TimeUnit.MILLISECONDS,
        lifecycleGracePeriod,
        lifecycleGracePeriodUnit,
        dispatchQueueBuilder,
        unstartedHealthStatusAndMessageSupplier,
        lifecycleGracePeriodHealthStatusAndMessageSupplier);

    this.healthCalculator = healthCalculator;
    this.supressUpdatesDuringLifecycleGracePeriod = supressUpdatesDuringLifecycleGracePeriod;
  }

  /**
   * Update the meter outside of the periodic cycle for a single occurrence. This method is thread
   * safe and may be invoked at any time.
   */
  @Override
  public final void mark()
  {
    mark(1);
  }

  /**
   * Update the meter outside of the periodic cycle. This method is thread safe and may be invoked
   * at any time.
   *
   * @param occurrences
   *          the number of occurrences to add to the meter
   */
  @Override
  public final void mark(final long occurrences)
  {
    // This is a bit hacky, but we are not concerned with absolute ordering or ensuring that we
    // catch every event during the transition from grace period to normal operation. As such,
    // we just check the last known state of each field involved in the decision making and go
    // on about our way.

    final AtomicBoolean inGracePeriod = getInLifecycleGracePeriod();
    final Meter currentMeter = meter;

    if (inGracePeriod != null && inGracePeriod.get() && supressUpdatesDuringLifecycleGracePeriod)
    {
      log.trace("[{}]: Suppressing mark [{}] during lifecycle grace period.", occurrences, getId());
    }
    else if (currentMeter != null)
    {
      meter.mark(occurrences);
    }
  }

  @Override
  protected PnkyPromise<Void> doInit()
  {
    meter = new Meter();
    return super.doInit();
  }

  @Override
  protected final PnkyPromise<HealthStatusAndMessage> calculateHealthStatusAndMessage()
  {
    return healthCalculator.apply(meter);
  }

  protected static abstract class AbstractPeriodicMeteringHealthCheckBuilder<T extends AbstractPeriodicMeteringHealthCheckBuilder<T>>
      extends AbstractAsyncPeriodicHealthCheckBuilder<T>
  {
    protected Function<Meter, PnkyPromise<HealthStatusAndMessage>> healthCalculator;
    protected boolean supressUpdatesDuringLifecycleGracePeriod = false;

    protected T healthCalculator(
        final Function<Meter, PnkyPromise<HealthStatusAndMessage>> healthCalculator)
    {
      this.healthCalculator = healthCalculator;
      return getThis();
    }

    protected T supressUpdatesDuringLifecycleGracePeriod(
        final boolean supressUpdatesDuringLifecycleGracePeriod)
    {
      this.supressUpdatesDuringLifecycleGracePeriod = supressUpdatesDuringLifecycleGracePeriod;
      return getThis();
    }
  }
}

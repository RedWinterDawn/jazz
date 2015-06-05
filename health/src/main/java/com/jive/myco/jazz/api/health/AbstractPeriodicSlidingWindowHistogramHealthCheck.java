package com.jive.myco.jazz.api.health;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.function.Supplier;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import com.codahale.metrics.Reservoir;
import com.codahale.metrics.SlidingTimeWindowReservoir;
import com.codahale.metrics.Snapshot;
import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.hawtdispatch.DispatchQueueBuilder;

/**
 * Base class for health checks backed by a histogram that is periodically updated and/or examined
 * to determine health status.
 * <p>
 * The health check may be updated with samples from the observed data stream via
 * {@link #update(long)} at any time.
 *
 * @author David Valeri
 */
@Slf4j
public abstract class AbstractPeriodicSlidingWindowHistogramHealthCheck extends
    AbstractAsyncPeriodicHealthCheck
{
  private final long window;
  private final TimeUnit windowUnit;
  private final Function<Snapshot, PnkyPromise<HealthStatusAndMessage>> healthCalculator;
  private final boolean supressUpdatesDuringLifecycleGracePeriod;

  private volatile SlidingTimeWindowReservoir reservoir;

  public AbstractPeriodicSlidingWindowHistogramHealthCheck(
      @NonNull final String id,
      final long period,
      @NonNull final TimeUnit periodUnit,
      final long lifecycleGracePeriod,
      @NonNull final TimeUnit lifecycleGracePeriodUnit,
      final DispatchQueueBuilder dispatchQueueBuilder,
      final Supplier<HealthStatusAndMessage> unstartedHealthStatusAndMessageSupplier,
      final Supplier<HealthStatusAndMessage> lifecycleGracePeriodHealthStatusAndMessageSupplier,
      final long window,
      @NonNull final TimeUnit windowUnit,
      @NonNull final Function<Snapshot, PnkyPromise<HealthStatusAndMessage>> healthCalculator,
      final boolean supressUpdatesDuringLifecycleGracePeriod)
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

    this.windowUnit = windowUnit;
    this.window = window;
    this.healthCalculator = healthCalculator;
    this.supressUpdatesDuringLifecycleGracePeriod = supressUpdatesDuringLifecycleGracePeriod;
  }

  /**
   * Update the reservoir outside of the periodic cycle. This method is thread safe and may be
   * invoked at any time.
   *
   * @param value
   *          the value to add to record in the reservoir
   */
  public final void update(final long value)
  {
    // This is a bit hacky, but we are not concerned with absolute ordering or ensuring that we
    // catch every event during the transition from grace period to normal operation. As such,
    // we just check the last known state of each field involved in the decision making and go
    // on about our way.

    final AtomicBoolean inGracePeriod = getInLifecycleGracePeriod();
    final Reservoir currentReservoir = reservoir;

    if (inGracePeriod != null && inGracePeriod.get() && supressUpdatesDuringLifecycleGracePeriod)
    {
      log.trace("[{}]: Suppressing update [{}] during lifecycle grace period.", value, getId());
    }
    else if (currentReservoir != null)
    {
      currentReservoir.update(value);
    }

  }

  @Override
  protected PnkyPromise<Void> doInit()
  {
    reservoir = new SlidingTimeWindowReservoir(window, windowUnit);
    return super.doInit();
  }

  @Override
  protected PnkyPromise<HealthStatusAndMessage> calculateHealthStatusAndMessage()
  {
    return healthCalculator.apply(reservoir.getSnapshot());
  }

  public abstract static class AbstractPeriodicSlidingWindowHistogramHealthCheckBuilder<T extends AbstractPeriodicSlidingWindowHistogramHealthCheckBuilder<T>>
      extends AbstractAsyncPeriodicHealthCheckBuilder<T>
  {
    protected long window = 30000L;
    protected TimeUnit windowUnit = TimeUnit.MILLISECONDS;
    protected Function<Snapshot, PnkyPromise<HealthStatusAndMessage>> healthCalculator;
    protected boolean supressUpdatesDuringLifecycleGracePeriod = false;

    protected T window(final long window)
    {
      this.window = window;
      return getThis();
    }

    protected T windowUnit(final TimeUnit windowUnit)
    {
      this.windowUnit = windowUnit;
      return getThis();
    }

    protected T healthCalculator(
        final Function<Snapshot, PnkyPromise<HealthStatusAndMessage>> healthCalculator)
    {
      this.healthCalculator = healthCalculator;
      return getThis();
    }

    public T supressUpdatesDuringLifecycleGracePeriod(
        final boolean supressUpdatesDuringLifecycleGracePeriod)
    {
      this.supressUpdatesDuringLifecycleGracePeriod = supressUpdatesDuringLifecycleGracePeriod;
      return getThis();
    }
  }
}

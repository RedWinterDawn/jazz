package com.jive.myco.jazz.api.health;

import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import com.codahale.metrics.Meter;
import com.jive.myco.commons.concurrent.Pnky;
import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.hawtdispatch.DispatchQueueBuilder;

/**
 * A concrete implementation of a meter based health check that supports basic health status changes
 * based on simple thresholds evaluated against the one minute exponentially weighted average rate
 * for the meter. The exponentially weighted average provides hysteresis to prevent rapid flapping
 * of state. Chose your thresholds with this aspect in mind.
 *
 * @author David Valeri
 */
@Slf4j
public final class PeriodicThresholdMeteringHealthCheck extends AbstractPeriodicMeteringHealthCheck
{
  public static enum ThresholdMode
  {
    UPPER_BOUND,
    LOWER_BOUND;
  }

  private PeriodicThresholdMeteringHealthCheck(
      final String id,
      final long period,
      final TimeUnit periodUnit,
      final long lifecycleGracePeriod,
      @NonNull final TimeUnit lifecycleGracePeriodUnit,
      final DispatchQueueBuilder dispatchQueueBuilder,
      final Supplier<HealthStatusAndMessage> unstartedHealthStatusAndMessageSupplier,
      final Supplier<HealthStatusAndMessage> lifecycleGracePeriodHealthStatusAndMessageSupplier,
      final boolean supressUpdatesDuringLifecycleGracePeriod,
      @NonNull final ThresholdMode thresholdMode,
      final double infoThreshold,
      final double warnThreshold,
      final double criticalThreshold)
  {
    super(
        id,
        period,
        periodUnit,
        lifecycleGracePeriod,
        lifecycleGracePeriodUnit,
        dispatchQueueBuilder,
        unstartedHealthStatusAndMessageSupplier,
        lifecycleGracePeriodHealthStatusAndMessageSupplier,
        (m) -> PeriodicThresholdMeteringHealthCheck.calculate(
            id, m, thresholdMode, infoThreshold, warnThreshold, criticalThreshold),
        supressUpdatesDuringLifecycleGracePeriod);
  }

  private static PnkyPromise<HealthStatusAndMessage> calculate(
      final String id,
      final Meter meter,
      final ThresholdMode thresholdMode,
      final double infoThreshold,
      final double warnThreshold,
      final double criticalThreshold)
  {
    log.debug("[{}]: Calculating status from meter [count={}, oneMinuteRate={}].",
        id, meter.getCount(), meter.getOneMinuteRate());

    HealthStatusAndMessage hsam = new HealthStatusAndMessage(HealthStatus.OK);

    final BiFunction<Double, Double, Boolean> compator;

    switch (thresholdMode)
    {
      case LOWER_BOUND:
        compator = (r, t) -> r < t;
        break;
      case UPPER_BOUND:
        compator = (r, t) -> r > t;
        break;
      default:
        throw new IllegalArgumentException("Unknown threshold mode [" + thresholdMode + "].");
    }

    if (compator.apply(meter.getOneMinuteRate(), criticalThreshold))
    {
      hsam = new HealthStatusAndMessage(HealthStatus.CRITICAL);
    }
    else if (compator.apply(meter.getOneMinuteRate(), warnThreshold))
    {
      hsam = new HealthStatusAndMessage(HealthStatus.WARN);
    }
    else if (compator.apply(meter.getOneMinuteRate(), infoThreshold))
    {
      hsam = new HealthStatusAndMessage(HealthStatus.INFO);
    }
    else
    {
      hsam = new HealthStatusAndMessage(HealthStatus.OK);
    }

    log.debug("[{}]: Calculating status as [{}].", id, hsam);

    return Pnky.immediatelyComplete(hsam);
  }

  public static ThresholdMeteringHealthCheckBuilder builder()
  {
    return new ThresholdMeteringHealthCheckBuilder();
  }

  public static final class ThresholdMeteringHealthCheckBuilder extends
      AbstractPeriodicMeteringHealthCheckBuilder<ThresholdMeteringHealthCheckBuilder>
  {
    private ThresholdMode thresholdMode = ThresholdMode.UPPER_BOUND;
    private double infoThreshold;
    private double warnThreshold;
    private double criticalThreshold;

    public PeriodicThresholdMeteringHealthCheck build()
    {
      return new PeriodicThresholdMeteringHealthCheck(
          id,
          period,
          periodUnit,
          lifecycleGracePeriod,
          lifecycleGracePeriodUnit,
          dispatchQueueBuilder,
          unstartedHealthStatusAndMessageSupplier,
          lifecycleGracePeriodHealthStatusAndMessageSupplier,
          supressUpdatesDuringLifecycleGracePeriod,
          thresholdMode,
          infoThreshold,
          warnThreshold,
          criticalThreshold);
    }

    public ThresholdMeteringHealthCheckBuilder thresholdMode(final ThresholdMode thresholdMode)
    {
      this.thresholdMode = thresholdMode;
      return this;
    }

    public ThresholdMeteringHealthCheckBuilder infoThreshold(final double infoThreshold)
    {
      this.infoThreshold = infoThreshold;
      return this;
    }

    public ThresholdMeteringHealthCheckBuilder warnThreshold(final double warnThreshold)
    {
      this.warnThreshold = warnThreshold;
      return this;
    }

    public ThresholdMeteringHealthCheckBuilder criticalThreshold(final double criticalThreshold)
    {
      this.criticalThreshold = criticalThreshold;
      return this;
    }

    @Override
    public ThresholdMeteringHealthCheckBuilder id(final String id)
    {
      return super.id(id);
    }

    @Override
    public ThresholdMeteringHealthCheckBuilder period(final long period)
    {
      return super.period(period);
    }

    @Override
    public ThresholdMeteringHealthCheckBuilder periodUnit(final TimeUnit periodUnit)
    {
      return super.periodUnit(periodUnit);
    }

    @Override
    public ThresholdMeteringHealthCheckBuilder lifecycleGracePeriod(
        final long lifecycleGracePeriod)
    {
      return super.lifecycleGracePeriod(lifecycleGracePeriod);
    }

    @Override
    public ThresholdMeteringHealthCheckBuilder lifecycleGracePeriodUnit(
        final TimeUnit lifecycleGracePeriodUnit)
    {
      return super.lifecycleGracePeriodUnit(lifecycleGracePeriodUnit);
    }

    @Override
    public ThresholdMeteringHealthCheckBuilder dispatchQueueBuilder(
        final DispatchQueueBuilder dispatchQueueBuilder)
    {
      return super.dispatchQueueBuilder(dispatchQueueBuilder);
    }

    @Override
    public ThresholdMeteringHealthCheckBuilder defaultUnstartedHealthStatusAndMessageSupplier(
        final Supplier<HealthStatusAndMessage> unstartedHealthStatusAndMessageSupplier)
    {
      return super.defaultUnstartedHealthStatusAndMessageSupplier(
          unstartedHealthStatusAndMessageSupplier);
    }

    @Override
    public ThresholdMeteringHealthCheckBuilder lifecycleGracePeriodHealthStatusAndMessageSupplier(
        final Supplier<HealthStatusAndMessage> lifecycleGracePeriodHealthStatusAndMessageSupplier)
    {
      return super.lifecycleGracePeriodHealthStatusAndMessageSupplier(
          lifecycleGracePeriodHealthStatusAndMessageSupplier);
    }

    @Override
    public ThresholdMeteringHealthCheckBuilder supressUpdatesDuringLifecycleGracePeriod(
        final boolean supressUpdatesDuringLifecycleGracePeriod)
    {
      return super
          .supressUpdatesDuringLifecycleGracePeriod(supressUpdatesDuringLifecycleGracePeriod);
    }

    @Override
    protected ThresholdMeteringHealthCheckBuilder getThis()
    {
      return this;
    }
  }
}

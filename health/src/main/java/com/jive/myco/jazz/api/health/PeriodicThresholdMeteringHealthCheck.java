package com.jive.myco.jazz.api.health;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import com.jive.myco.commons.concurrent.Pnky;
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

  /**
   * @deprecated use {@link com.jive.myco.jazz.api.health.ThresholdMode} instead
   */
  @Deprecated
  @RequiredArgsConstructor
  @Getter
  public static enum ThresholdMode
  {
    UPPER_BOUND(com.jive.myco.jazz.api.health.ThresholdMode.UPPER_BOUND),
    LOWER_BOUND(com.jive.myco.jazz.api.health.ThresholdMode.LOWER_BOUND);

    @NonNull
    private final com.jive.myco.jazz.api.health.ThresholdMode newThresholdMode;
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
      @NonNull final com.jive.myco.jazz.api.health.ThresholdMode thresholdMode,
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
        (m) -> Pnky.immediatelyComplete(
            ThresholdHealthCheckUtil.calculateHealthStatusAndMessage(
                log,
                id,
                thresholdMode,
                m.getOneMinuteRate(),
                infoThreshold,
                warnThreshold,
                criticalThreshold)),
        supressUpdatesDuringLifecycleGracePeriod);
  }

  public static ThresholdMeteringHealthCheckBuilder builder()
  {
    return new ThresholdMeteringHealthCheckBuilder();
  }

  @Accessors(fluent = true)
  @Setter
  public static final class ThresholdMeteringHealthCheckBuilder extends
      AbstractPeriodicMeteringHealthCheckBuilder<ThresholdMeteringHealthCheckBuilder>
  {
    private com.jive.myco.jazz.api.health.ThresholdMode thresholdMode =
        com.jive.myco.jazz.api.health.ThresholdMode.UPPER_BOUND;
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

    /**
     * @deprecated use {@link #thresholdMode(com.jive.myco.jazz.api.health.ThresholdMode)} instead
     */
    @Deprecated
    public ThresholdMeteringHealthCheckBuilder thresholdMode(
        final ThresholdMode thresholdMode)
    {
      this.thresholdMode = thresholdMode.getNewThresholdMode();
      return this;
    }

    public ThresholdMeteringHealthCheckBuilder thresholdMode(
        final com.jive.myco.jazz.api.health.ThresholdMode thresholdMode)
    {
      this.thresholdMode = thresholdMode;
      return this;
    }

    @Override
    protected ThresholdMeteringHealthCheckBuilder getThis()
    {
      return this;
    }
  }
}

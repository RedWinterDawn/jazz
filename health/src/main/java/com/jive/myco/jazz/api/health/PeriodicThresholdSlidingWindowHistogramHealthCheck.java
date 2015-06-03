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
public final class PeriodicThresholdSlidingWindowHistogramHealthCheck extends
    AbstractPeriodicSlidingWindowHistogramHealthCheck
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

  private PeriodicThresholdSlidingWindowHistogramHealthCheck(
      final String id,
      final long period,
      final TimeUnit periodUnit,
      final long lifecycleGracePeriod,
      @NonNull final TimeUnit lifecycleGracePeriodUnit,
      final DispatchQueueBuilder dispatchQueueBuilder,
      final Supplier<HealthStatusAndMessage> unstartedHealthStatusAndMessageSupplier,
      final Supplier<HealthStatusAndMessage> lifecycleGracePeriodHealthStatusAndMessageSupplier,
      final boolean supressUpdatesDuringLifecycleGracePeriod,
      final long window,
      @NonNull final TimeUnit windowUnit,
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
        window,
        windowUnit,
        (s) -> Pnky.immediatelyComplete(ThresholdHealthCheckUtil
            .calculateHealthStatusAndMessage(
                log,
                id,
                thresholdMode,
                s.get99thPercentile(),
                infoThreshold,
                warnThreshold,
                criticalThreshold)),
        supressUpdatesDuringLifecycleGracePeriod);
  }

  public static PeriodicThresholdSlidingWindowHistogramHealthCheckBuilder builder()
  {
    return new PeriodicThresholdSlidingWindowHistogramHealthCheckBuilder();
  }

  @Accessors(fluent = true)
  @Setter
  public static final class PeriodicThresholdSlidingWindowHistogramHealthCheckBuilder
      extends
      AbstractPeriodicSlidingWindowHistogramHealthCheckBuilder<PeriodicThresholdSlidingWindowHistogramHealthCheckBuilder>
  {
    private com.jive.myco.jazz.api.health.ThresholdMode thresholdMode =
        com.jive.myco.jazz.api.health.ThresholdMode.UPPER_BOUND;
    private double infoThreshold;
    private double warnThreshold;
    private double criticalThreshold;

    public PeriodicThresholdSlidingWindowHistogramHealthCheck build()
    {
      return new PeriodicThresholdSlidingWindowHistogramHealthCheck(
          id,
          period,
          periodUnit,
          lifecycleGracePeriod,
          lifecycleGracePeriodUnit,
          dispatchQueueBuilder,
          unstartedHealthStatusAndMessageSupplier,
          lifecycleGracePeriodHealthStatusAndMessageSupplier,
          supressUpdatesDuringLifecycleGracePeriod,
          window,
          windowUnit,
          thresholdMode,
          infoThreshold,
          warnThreshold,
          criticalThreshold);
    }

    @Override
    public PeriodicThresholdSlidingWindowHistogramHealthCheckBuilder window(final long window)
    {
      return super.window(window);
    }

    @Override
    public PeriodicThresholdSlidingWindowHistogramHealthCheckBuilder windowUnit(
        final TimeUnit windowUnit)
    {
      return super.windowUnit(windowUnit);
    }

    /**
     * @deprecated use {@link #thresholdMode(com.jive.myco.jazz.api.health.ThresholdMode)} instead
     */
    @Deprecated
    public PeriodicThresholdSlidingWindowHistogramHealthCheckBuilder thresholdMode(
        final ThresholdMode thresholdMode)
    {
      this.thresholdMode = thresholdMode.getNewThresholdMode();
      return this;
    }

    public PeriodicThresholdSlidingWindowHistogramHealthCheckBuilder thresholdMode(
        final com.jive.myco.jazz.api.health.ThresholdMode thresholdMode)
    {
      this.thresholdMode = thresholdMode;
      return this;
    }

    @Override
    public PeriodicThresholdSlidingWindowHistogramHealthCheckBuilder id(final String id)
    {
      return super.id(id);
    }

    @Override
    public PeriodicThresholdSlidingWindowHistogramHealthCheckBuilder period(final long period)
    {
      return super.period(period);
    }

    @Override
    public PeriodicThresholdSlidingWindowHistogramHealthCheckBuilder periodUnit(
        final TimeUnit periodUnit)
    {
      return super.periodUnit(periodUnit);
    }

    @Override
    public PeriodicThresholdSlidingWindowHistogramHealthCheckBuilder lifecycleGracePeriod(
        final long lifecycleGracePeriod)
    {
      return super.lifecycleGracePeriod(lifecycleGracePeriod);
    }

    @Override
    public PeriodicThresholdSlidingWindowHistogramHealthCheckBuilder lifecycleGracePeriodUnit(
        final TimeUnit lifecycleGracePeriodUnit)
    {
      return super.lifecycleGracePeriodUnit(lifecycleGracePeriodUnit);
    }

    @Override
    public PeriodicThresholdSlidingWindowHistogramHealthCheckBuilder dispatchQueueBuilder(
        final DispatchQueueBuilder dispatchQueueBuilder)
    {
      return super.dispatchQueueBuilder(dispatchQueueBuilder);
    }

    @Override
    public PeriodicThresholdSlidingWindowHistogramHealthCheckBuilder defaultUnstartedHealthStatusAndMessageSupplier(
        final Supplier<HealthStatusAndMessage> unstartedHealthStatusAndMessageSupplier)
    {
      return super.defaultUnstartedHealthStatusAndMessageSupplier(
          unstartedHealthStatusAndMessageSupplier);
    }

    @Override
    public PeriodicThresholdSlidingWindowHistogramHealthCheckBuilder lifecycleGracePeriodHealthStatusAndMessageSupplier(
        final Supplier<HealthStatusAndMessage> lifecycleGracePeriodHealthStatusAndMessageSupplier)
    {
      return super.lifecycleGracePeriodHealthStatusAndMessageSupplier(
          lifecycleGracePeriodHealthStatusAndMessageSupplier);
    }

    @Override
    public PeriodicThresholdSlidingWindowHistogramHealthCheckBuilder supressUpdatesDuringLifecycleGracePeriod(
        final boolean supressUpdatesDuringLifecycleGracePeriod)
    {
      return super
          .supressUpdatesDuringLifecycleGracePeriod(supressUpdatesDuringLifecycleGracePeriod);
    }

    @Override
    protected PeriodicThresholdSlidingWindowHistogramHealthCheckBuilder getThis()
    {
      return this;
    }
  }
}

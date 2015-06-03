package com.jive.myco.jazz.api.health;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.hawtdispatch.DispatchQueueBuilder;

/**
 * Base class for health checks backed by a "gauge" reporting an arbitrary instantaneous value that
 * is periodically examined to determine health status. This health check does not have built-in
 * hysteresis so the supplier serving as the gauge should ensure that such damping is in effect when
 * required.
 *
 * @author David Valeri
 */
@Slf4j
public final class PeriodicThresholdGaugeHealthCheck<T extends Comparable<T>> extends
    AbstractAsyncPeriodicHealthCheck
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

  private final Supplier<PnkyPromise<T>> dataSupplier;
  private final com.jive.myco.jazz.api.health.ThresholdMode thresholdMode;
  private final T infoThreshold;
  private final T warnThreshold;
  private final T criticalThreshold;

  private PeriodicThresholdGaugeHealthCheck(
      @NonNull final String id,
      final long period,
      @NonNull final TimeUnit periodUnit,
      final long lifecycleGracePeriod,
      @NonNull final TimeUnit lifecycleGracePeriodUnit,
      final DispatchQueueBuilder dispatchQueueBuilder,
      final Supplier<HealthStatusAndMessage> unstartedHealthStatusAndMessageSupplier,
      final Supplier<HealthStatusAndMessage> lifecycleGracePeriodHealthStatusAndMessageSupplier,
      final Supplier<PnkyPromise<T>> dataSupplier,
      @NonNull final com.jive.myco.jazz.api.health.ThresholdMode thresholdMode,
      final T infoThreshold,
      final T warnThreshold,
      final T criticalThreshold)
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

    this.dataSupplier = dataSupplier;
    this.thresholdMode = thresholdMode;
    this.infoThreshold = infoThreshold;
    this.warnThreshold = warnThreshold;
    this.criticalThreshold = criticalThreshold;
  }

  @Override
  protected PnkyPromise<HealthStatusAndMessage> calculateHealthStatusAndMessage()
  {
    return dataSupplier.get()
        .thenTransform((value) ->
        {
          return ThresholdHealthCheckUtil.<T> calculateHealthStatusAndMessage(
              log,
              getId(),
              thresholdMode,
              value,
              infoThreshold,
              warnThreshold,
              criticalThreshold);
        });
  }

  public static <T extends Comparable<T>> PeriodicThresholdGaugeHealthCheckBuilder<T> builder()
  {
    return new PeriodicThresholdGaugeHealthCheckBuilder<>();
  }

  @Accessors(fluent = true)
  @Setter
  public static final class PeriodicThresholdGaugeHealthCheckBuilder<T extends Comparable<T>>
      extends AbstractAsyncPeriodicHealthCheckBuilder<PeriodicThresholdGaugeHealthCheckBuilder<T>>
  {
    private Supplier<PnkyPromise<T>> dataSupplier;
    private com.jive.myco.jazz.api.health.ThresholdMode thresholdMode =
        com.jive.myco.jazz.api.health.ThresholdMode.UPPER_BOUND;
    private T infoThreshold;
    private T warnThreshold;
    private T criticalThreshold;

    public PeriodicThresholdGaugeHealthCheck<T> build()
    {
      return new PeriodicThresholdGaugeHealthCheck<>(
          id,
          period,
          periodUnit,
          lifecycleGracePeriod,
          lifecycleGracePeriodUnit,
          dispatchQueueBuilder,
          unstartedHealthStatusAndMessageSupplier,
          lifecycleGracePeriodHealthStatusAndMessageSupplier,
          dataSupplier,
          thresholdMode,
          infoThreshold,
          warnThreshold,
          criticalThreshold);
    }

    @Override
    public PeriodicThresholdGaugeHealthCheckBuilder<T> id(final String id)
    {
      return super.id(id);
    }

    @Override
    public PeriodicThresholdGaugeHealthCheckBuilder<T> period(final long period)
    {
      return super.period(period);
    }

    @Override
    public PeriodicThresholdGaugeHealthCheckBuilder<T> periodUnit(final TimeUnit periodUnit)
    {
      return super.periodUnit(periodUnit);
    }

    @Override
    public PeriodicThresholdGaugeHealthCheckBuilder<T> lifecycleGracePeriod(
        final long lifecycleGracePeriod)
    {
      return super.lifecycleGracePeriod(lifecycleGracePeriod);
    }

    @Override
    public PeriodicThresholdGaugeHealthCheckBuilder<T> lifecycleGracePeriodUnit(
        final TimeUnit lifecycleGracePeriodUnit)
    {
      return super.lifecycleGracePeriodUnit(lifecycleGracePeriodUnit);
    }

    @Override
    public PeriodicThresholdGaugeHealthCheckBuilder<T> dispatchQueueBuilder(
        final DispatchQueueBuilder dispatchQueueBuilder)
    {
      return super.dispatchQueueBuilder(dispatchQueueBuilder);
    }

    @Override
    public PeriodicThresholdGaugeHealthCheckBuilder<T> defaultUnstartedHealthStatusAndMessageSupplier(
        final Supplier<HealthStatusAndMessage> unstartedHealthStatusAndMessageSupplier)
    {
      return super.defaultUnstartedHealthStatusAndMessageSupplier(
          unstartedHealthStatusAndMessageSupplier);
    }

    @Override
    public PeriodicThresholdGaugeHealthCheckBuilder<T> lifecycleGracePeriodHealthStatusAndMessageSupplier(
        final Supplier<HealthStatusAndMessage> lifecycleGracePeriodHealthStatusAndMessageSupplier)
    {
      return super.lifecycleGracePeriodHealthStatusAndMessageSupplier(
          lifecycleGracePeriodHealthStatusAndMessageSupplier);
    }

    public PeriodicThresholdGaugeHealthCheckBuilder<T> dataSupplier(
        final Supplier<PnkyPromise<T>> dataSupplier)
    {
      this.dataSupplier = dataSupplier;
      return this;
    }

    /**
     * @deprecated use {@link #thresholdMode(com.jive.myco.jazz.api.health.ThresholdMode)} instead
     */
    @Deprecated
    public PeriodicThresholdGaugeHealthCheckBuilder<T> thresholdMode(
        final ThresholdMode thresholdMode)
    {
      this.thresholdMode = thresholdMode.getNewThresholdMode();
      return this;
    }

    public PeriodicThresholdGaugeHealthCheckBuilder<T> thresholdMode(
        final com.jive.myco.jazz.api.health.ThresholdMode thresholdMode)
    {
      this.thresholdMode = thresholdMode;
      return this;
    }

    @Override
    protected PeriodicThresholdGaugeHealthCheckBuilder<T> getThis()
    {
      return this;
    }
  }
}

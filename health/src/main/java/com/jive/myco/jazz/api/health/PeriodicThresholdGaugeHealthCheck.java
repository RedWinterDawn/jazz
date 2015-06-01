package com.jive.myco.jazz.api.health;

import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import lombok.Builder;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import com.jive.myco.commons.concurrent.Pnky;
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
  public static enum ThresholdMode
  {
    UPPER_BOUND,
    LOWER_BOUND;
  }

  private final Supplier<PnkyPromise<T>> dataSupplier;
  private final ThresholdMode thresholdMode;
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
      @NonNull final ThresholdMode thresholdMode,
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
        .thenCompose((value) ->
        {
          log.debug("[{}]: Calculating status from gauge [{].", getId(), value);

          HealthStatusAndMessage hsam = new HealthStatusAndMessage(HealthStatus.OK);

          final BiFunction<T, T, Boolean> compator;

          switch (thresholdMode)
          {
            case LOWER_BOUND:
              compator = (r, t) -> r.compareTo(t) < 0;
              break;
            case UPPER_BOUND:
              compator = (r, t) -> r.compareTo(t) > 0;
              break;
            default:
              throw new IllegalArgumentException("Unknown threshold mode [" + thresholdMode
                  + "].");
          }

          if (compator.apply(value, criticalThreshold))
          {
            hsam = new HealthStatusAndMessage(HealthStatus.CRITICAL);
          }
          else if (compator.apply(value, warnThreshold))
          {
            hsam = new HealthStatusAndMessage(HealthStatus.WARN);
          }
          else if (compator.apply(value, infoThreshold))
          {
            hsam = new HealthStatusAndMessage(HealthStatus.INFO);
          }
          else
          {
            hsam = new HealthStatusAndMessage(HealthStatus.OK);
          }

          log.debug("[{}]: Calculating status as [{}].", getId(), hsam);

          return Pnky.immediatelyComplete(hsam);
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
    private final ThresholdMode thresholdMode = ThresholdMode.UPPER_BOUND;
    private String id;
    private Supplier<PnkyPromise<T>> dataSupplier;
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
    protected PeriodicThresholdGaugeHealthCheckBuilder<T> getThis()
    {
      return this;
    }
  }
}

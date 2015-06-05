package com.jive.myco.jazz.api.health;

import java.util.Comparator;
import java.util.function.BiFunction;

import lombok.experimental.UtilityClass;

import org.slf4j.Logger;

/**
 * A utility class for implementing threshold based health checks.
 *
 * @author David Valeri
 */
@UtilityClass
class ThresholdHealthCheckUtil
{
  public static HealthStatusAndMessage calculateHealthStatusAndMessage(
      final Logger log,
      final String id,
      final ThresholdMode thresholdMode,
      final short value,
      final short infoThreshold,
      final short warnThreshold,
      final short criticalThreshold)
  {
    return calculateHealthStatusAndMessageInternal(
        log,
        id,
        thresholdMode,
        value,
        infoThreshold,
        warnThreshold,
        criticalThreshold,
        Short::compare);
  }

  public static HealthStatusAndMessage calculateHealthStatusAndMessage(
      final Logger log,
      final String id,
      final ThresholdMode thresholdMode,
      final int value,
      final int infoThreshold,
      final int warnThreshold,
      final int criticalThreshold)
  {
    return calculateHealthStatusAndMessageInternal(
        log,
        id,
        thresholdMode,
        value,
        infoThreshold,
        warnThreshold,
        criticalThreshold,
        Integer::compare);
  }

  public static HealthStatusAndMessage calculateHealthStatusAndMessage(
      final Logger log,
      final String id,
      final ThresholdMode thresholdMode,
      final long value,
      final long infoThreshold,
      final long warnThreshold,
      final long criticalThreshold)
  {
    return calculateHealthStatusAndMessageInternal(
        log,
        id,
        thresholdMode,
        value,
        infoThreshold,
        warnThreshold,
        criticalThreshold,
        Long::compare);
  }

  public static HealthStatusAndMessage calculateHealthStatusAndMessage(
      final Logger log,
      final String id,
      final ThresholdMode thresholdMode,
      final float value,
      final float infoThreshold,
      final float warnThreshold,
      final float criticalThreshold)
  {
    return calculateHealthStatusAndMessageInternal(
        log,
        id,
        thresholdMode,
        value,
        infoThreshold,
        warnThreshold,
        criticalThreshold,
        Float::compare);
  }

  public static HealthStatusAndMessage calculateHealthStatusAndMessage(
      final Logger log,
      final String id,
      final ThresholdMode thresholdMode,
      final double value,
      final double infoThreshold,
      final double warnThreshold,
      final double criticalThreshold)
  {
    return calculateHealthStatusAndMessageInternal(
        log,
        id,
        thresholdMode,
        value,
        infoThreshold,
        warnThreshold,
        criticalThreshold,
        Double::compare);
  }

  public static <T extends Comparable<T>> HealthStatusAndMessage calculateHealthStatusAndMessage(
      final Logger log,
      final String id,
      final ThresholdMode thresholdMode,
      final T value,
      final T infoThreshold,
      final T warnThreshold,
      final T criticalThreshold)
  {
    return calculateHealthStatusAndMessageInternal(
        log,
        id,
        thresholdMode,
        value,
        infoThreshold,
        warnThreshold,
        criticalThreshold,
        T::compareTo);
  }

  private static <T> HealthStatusAndMessage calculateHealthStatusAndMessageInternal(
      final Logger log,
      final String id,
      final ThresholdMode thresholdMode,
      final T value,
      final T infoThreshold,
      final T warnThreshold,
      final T criticalThreshold,
      final Comparator<T> comparator)
  {
    log.trace("[{}]: Calculating status from [{}].", id, value);

    HealthStatusAndMessage hsam = new HealthStatusAndMessage(HealthStatus.OK);

    final BiFunction<T, T, Boolean> compatorFunc;

    switch (thresholdMode)
    {
      case LOWER_BOUND:
        compatorFunc = (r, t) -> comparator.compare(r, t) < 0;
        break;
      case UPPER_BOUND:
        compatorFunc = (r, t) -> comparator.compare(r, t) > 0;
        break;
      default:
        throw new IllegalArgumentException("Unknown threshold mode [" + thresholdMode + "].");
    }

    if (compatorFunc.apply(value, criticalThreshold))
    {
      hsam = createHealthStatusAndMessage(HealthStatus.CRITICAL, value, criticalThreshold);
    }
    else if (compatorFunc.apply(value, warnThreshold))
    {
      hsam = createHealthStatusAndMessage(HealthStatus.WARN, value, warnThreshold);
    }
    else if (compatorFunc.apply(value, infoThreshold))
    {
      hsam = createHealthStatusAndMessage(HealthStatus.INFO, value, infoThreshold);
    }
    else
    {
      hsam = new HealthStatusAndMessage(HealthStatus.OK);
    }

    log.trace("[{}]: Calculated status as [{}].", id, hsam);

    return hsam;
  }

  private static <T> HealthStatusAndMessage createHealthStatusAndMessage(
      final HealthStatus healthStatus,
      final T value,
      final T threshold)
  {
    return new HealthStatusAndMessage(
        healthStatus,
        String.format(
            "Value [%s] has violated threshold [%s]",
            value,
            threshold));
  }
}

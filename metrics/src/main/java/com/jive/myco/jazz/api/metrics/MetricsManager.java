package com.jive.myco.jazz.api.metrics;

import com.codahale.metrics.Metric;
import com.jive.myco.commons.lifecycle.ListenableLifecycled;

/**
 * A manager for working with Jazz metrics.
 *
 * @author David Valeri
 */
public interface MetricsManager extends ListenableLifecycled
{
  /**
   * Removes a metric from the registry.
   *
   * @param metric the metric to remove from the registry
   */
  void removeMetric(final Metric metric);

  /**
   * Returns a new context with no prefix on all metrics created from the returned context.
   */
  MetricsManagerContext segment();

  /**
   * Returns a new context with {@code segment} and {@code additionalSegments} prefixed to all
   * metrics created from the returned context.
   *
   * @param segment
   *          the segment to add to the metric names for all metrics created by the new context
   * @param additionalSegments
   *          more segments to add to all metric names created by the new context
   */
  MetricsManagerContext segment(final String segment, final String... additionalSegments);
}
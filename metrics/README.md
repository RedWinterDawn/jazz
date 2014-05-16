# Jazz API Metrics

This module provides interfaces used to interact with a metrics library, currently Coda Hale Metrics.  This API attempts to ensure uniform metric naming and provide easy lifecycle management of metrics as they are created and destroyed.

* **[Usage](#usage)**
* **[Change Log](../README.md#changes)**

## <a name="usage"></a>Usage


The example code below demonstrates how to manage the lifecycle of the  and to leverage *MetricsManager* and *MetricsManagerContext*s to provide uniform naming of metrics within an application.


```java    
// Create a context for some example component in the application.
// All metrics created under this context share a common name prefix.
final MetricsManagerContext exampleComponentMmc = metricsManager.segment(
    "example-application", "example-component");

// You can pass a MetricsManagerContext to a sub-component to derive another
// context and it will automatically inherit the parent contexts name prefix.
final MetricsManagerContext exampleSubComponentMmc = exampleComponentMmc.segment(
    "example-sub-component");

// Initialize new metrics to use
Meter exampleComponentRate = exampleComponentMmc.getMeter("rate");
Meter exampleSubComponentRate = exampleSubComponentMmc.getMeter("rate");

// Use them
exampleComponentRate.mark();
exampleSubComponentRate.mark();

// Optionally discard the metrics when they are no longer needed.  The
// metrics manager performs discard via an object reference to the
// metric rather than by metric name.  This approach is much easier
// to use as you don't have to reconstruct all the metric names
// again when you want to destroy the metric.
exampleComponentMmc.getMetricsManager().removeMetric(
    exampleComponentRate);

// Discards are performed against the manager, not a single context.
// You can use any context, or the manager directly to discard a metric.
exampleComponentMmc.getMetricsManager().removeMetric(
    exampleSubComponentRate);
```
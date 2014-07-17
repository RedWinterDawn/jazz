# Jazz API Health

This module provides interfaces used to create health checks and register those checks with the
framework. 

* **[Usage](#usage)**
* **[Change Log](../README.md#changes)**

## <a name="usage"></a>Usage

### Registering Health Checks

To register a single Servlet, use the `HttpServerManager`, an injectable resource, to register
the Servlet instance on the desired network, IP, and/or port.

The network binding of the resource is resolved as follows:
 * If network ID is specified:
   * If IP address is specified, verify that it is on the specified network and use the specified
     IP address.
   * If IP address is not specified, choose the first IP on the specified network.
 * If network ID is not specified:
   * Verify IP address is specified
   * Verify IP address specified is available on a known network and use the resolved network ID.
 * If port is specified, use the specified port, otherwise use a random port

The following example uses the asynchronous API in a synchronous manner for demonstration purposes.

```java
    
    final TestHealthCheck healthCheck = new TestHealthCheck("check-1");
    
    // Describe the aggregate health check
    final AggregateHealthCheckDescriptor desrciptor = AggregateHealthCheckDescriptor.builder()
        .id("test-health-check-id")
        .addHealthCheck(healthCheck)
        .build();

    // Add an aggregate check, calling get() on the returned future to wait for the binding.
    // The binding is also an AggregateHealthCheck instance.
    final AggregateHealthCheckBinding binding =
        healthCheckManager.addAggregateHealthCheck(desrciptor).get();
    
    // A listener that receives updates when the aggregate health check's status changes
    AggregateHealthCheckListener listener = new AggregateHealthCheckListener()
    {
      public void onHealthCheckStatusChanged(final AggregateHealthCheck healthCheck,
          final HealthStatus status)
      {
        log.info(...);
      }
    }
    
    // Add the listener to the binding, calling get() on the returned future to wait for the listener
    // to be added.
    binding.addListener(listener).get();
    
    // Create another health check to add to the aggregate health check binding
    final TestHealthCheck healthCheck2 = new TestHealthCheck("check-2");
    
    // Add the new health check to the existing binding, Calling get() on the returned future to 
    // wait for the check to be added.
    binding.addHealthCheck(healthCheck2).get();
    
    // Remove the new health check, calling get() on the returned future to wait for the check to be
    // removed.
    binding.removeHealthCheck(healthCheck2).get(5, TimeUnit.SECONDS);
    
    // Remove the aggregate health check binding from the framework, calling get() on the returned
    // future to wait for the binding to be removed.
    binding.remove().get();
```

### Implementing Health Checks

The API provides the abstract `PeriodicHealthCheck` class to ease in the implementation of health
checks that update their status on a periodic basis.  See the JavaDoc on  
[src/main/java/com/jive/myco/jazz/api/health/PeriodicHealthCheck.java](src/main/java/com/jive/myco/jazz/api/health/PeriodicHealthCheck.java) 
for more details on implementing a custom periodic health check, specifically 
`calculateHealthStatus()` and `calculateHealthStatusSync()`.

To implement a health check that updates health status based on an external driver / event, 
implement `AbstractHealthCheck`.  See the JavaDoc on 
[src/main/java/com/jive/myco/jazz/api/health/AbstractHealthCheck.java](src/main/java/com/jive/myco/jazz/api/health/AbstractHealthCheck.java)
for more details, specifically `setHealthStatus(@NonNull final HealthStatus newHealthStatus)`.
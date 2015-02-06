# Jazz API REST

This module provides interfaces used to register / publish / expose REST Web Services.

* **[Usage](#usage)**
* **[Change Log](../README.md#changes)**

## <a name="usage"></a>Usage


### Registering a REST Web Service

To register a `Servlet`, use the `HttpServerManager` to register the `Servlet` instance(s) on 
the desired network, IP, and/or port as part of a web application.

The network binding of the resource is resolved as follows for each `ConnectorDescriptor` added:
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
    final RestServiceManager restServiceManager = ...;

    // A JAX-RS annotated class.
    final TestRestResourceController testResource = new TestRestResourceController();
    
    // A Servlet Filter
    final TestFilter testFilter = new TestFilter();

    // A JAX-RS Provider for JSON mapping
    final ObjectMapper objectMapper = new ObjectMapper();
    final JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider(objectMapper);

    // Describe the REST service resources to expose
    final RestServiceDescriptor restServiceDescriptor = RestServiceDescriptor.builder()
        .contextPath("/resources")
        .addConnector(
            ConnectorDescriptor.builder()
                .networkId(testNetwork.getId())
                .build())
        .addSingletons(testResource, jacksonJsonProvider)
        .addFilter(new FilterMappingDescriptor("/*", testFilter))
        .addStaticResource(
            StaticResourceDescriptor.builder()
                .relativeUrlPath("/files")
                .path("./src/test/resources/www")
                .build())
        .build();

    // Bind the REST resources to /resources on the test network using a random port.  Network 
    // information may be attained via the `JazzRuntimeEnvironment`.
    final PnkyPromise<RestServiceBinding> restServiceFuture = 
        restServiceManager.addService(restServiceDescriptor);
    
    final RestServiceBinding restServiceBinding = restServiceFuture.get();
    
    // Network binding information for the REST service is available from the returned binding.
    restServiceBinding.getConnectorBindings().stream().findFirst().get().getPort();

    // Remove the binding when done
    restServiceBinding.remove().get();
```

### Advertising in Service Discovery

`RestServiceDescriptor` has provisions for adding metadata used to publish the REST service in the
service registry after the REST service is bound and running.  Create and add an 
`AutoRegisteredServiceInstanceDescriptor` to the `RestServiceDescriptor` and the manager will
automatically publish each bound endpoint for the REST service in the service registry if the 
registry is available.

```java
    final RestServiceDescriptor restServiceDescriptor = RestServiceDescriptor.builder()
        .addSingletons(testResource, jacksonJsonProvider)
        .contextPath("/resources")
        .addConnector(ConnectorDescriptor.builder()
            .networkId(testNetwork.getId())
            .port(port1)
            .build())
        .addConnector(ConnectorDescriptor.builder()
            .networkId(testNetwork.getId())
            .port(port2)
            .build())
        .register(AutoRegisteredServiceInstanceDescriptor.builder()
            .serviceInterfaceName(ServiceInterfaceName.valueOf("interface"))
            .serviceInterfaceVersion(Version.parseVersion("1.0.0"))
            .build())
        .build()
```

### Controlling Shutdown

The manager and `RestServiceDescriptor` provide provisions for controlling the shutdown / removal
of the REST service from the manager.  By providing the descriptor with a 
`ConnectedAndRegisteredBindingGracefulShutdownHook` instance, the manager will quiery the shutdown
hook and respect the hooks directions with regards to removing the REST service from the service 
registry and ultimately the complete shutdown of the REST service.

```java
    final RestServiceDescriptor restServiceDescriptor = RestServiceDescriptor.builder()
        .addSingletons(testResource, jacksonJsonProvider)
        .contextPath("/resources")
        .addConnector(ConnectorDescriptor.builder()
            .networkId(testNetwork.getId())
            .port(port1)
            .build())
        .addConnector(ConnectorDescriptor.builder()
            .networkId(testNetwork.getId())
            .port(port2)
            .build())
        .register(AutoRegisteredServiceInstanceDescriptor.builder()
            .serviceInterfaceName(ServiceInterfaceName.valueOf("interface"))
            .serviceInterfaceVersion(Version.parseVersion("1.0.0"))
            .build())
        .gracefulShutdownHook(new AbstractConnectedAndRegisteredBindingGracefulShutdownHook()
        {
          @Override
          public PnkyPromise<Void> removeRegistrationRequested()
          {
            // Do something non-blocking or asynchronous to prepare for removal of the endpoint 
            // from the service registry.
            PnkyPromise<Void> result = ...
             
            return result;
          }

          @Override
          public PnkyPromise<Void> removeRequested()
          {
            // Do something non-blocking or asynchronous to prepare for shutdown of the endpoint 
            PnkyPromise<Void> result = ...
             
            return result;
          }
        })
        .build();
```
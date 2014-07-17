# Jazz API REST

This module provides interfaces used to register / publish / expose REST Web Services.

* **[Usage](#usage)**
* **[Change Log](../README.md#changes)**

## <a name="usage"></a>Usage


### Registering a REST Web Service

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
        .addSingletons(testResource, jacksonJsonProvider)
        .addFilter(new FilterMappingDescriptor("/*", testFilter))
        .addStaticResource(
            new StaticResourceDescriptor(
                "/files",
                "./src/test/resources/www"))
        .build();

    // Bind the REST resources to expose them
    final PnkyPromise<RestServiceBinding> restServiceFuture =
        restServiceManager.addService(restServiceDescriptor, "/resources", testNetwork.getId(),
            null, null);
    
    final RestServiceBinding restServiceBinding = restServiceFuture.get();
    
    // Network binding information for the REST service is available from the returned binding.
    restServiceBinding.getConnectorBindings().stream().findFirst().get().getPort();

    // Remove the binding when done
    restServiceBinding.remove().get();
```
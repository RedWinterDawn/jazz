# Jazz API Web

This module provides interfaces used to register / publish / expose Web application resources such
as Servlets and static resources.

* **[Usage](#usage)**
* **[Change Log](../README.md#changes)**

## <a name="usage"></a>Usage


### Registering a Single Servlet

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
    final HttpServerManager manager = ...;

    // Bind the servlet to /servlet1 on the test network using a random port.  Network information
    // may be attained via the JazzRuntime.
    final PnkyPromise<ServletBinding> servletFuture =
        manager.addServlet(new MyAwesomeServlet(), "/servlet1", testNetwork.getId(), null, null);
        
    final ServletBinding servletBinding = servletFuture.get();
          
    // Network binding information for the Servlet is available from the returned ServletBinding.  
    servletBinding.getConnectorBindings().stream().findFirst().get().getPort();
    
    // Remove the binding when done
    servletBinding.remove().get;
```

### Registering a Web Application

To register a collection of Servlets, Servlet Filters, and static resources, use the
`HttpServerManager`, an injectable resource, to register the resources instance on the desired 
network, IP, and/or port.

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
    final HttpServerManager manager = ...;

    final TestFilter testFilter = new TestFilter();

    // Create the descriptor with Filters, Servlets, and static resources.
    final WebAppDescriptor webAppDescriptor = WebAppDescriptor.builder()
        .addFilter(new FilterMappingDescriptor("/*", testFilter))
        .addServlet(new ServletMappingDescriptor("/servlet/*", new NoJspServlet()))
        .addStaticResource(
            new StaticResourceDescriptor(
                "files",
                "./src/test/resources/www"))
        .build();

    // Bind the servlet to /app1 on the test network using a random port.  Network information
    // may be attained via the JazzRuntime.
    final PnkyPromise<WebAppBinding> webAppFuture =
        manager.addWebApp(webAppDescriptor, "/app1", testNetwork.getId(), null, null);
        
    final WebAppBinding webAppBinding = webAppFuture.get();

    // Network binding information for the application is available from the returned ServletBinding.
    webAppBinding.getConnectorBindings().stream().findFirst().get().getPort();
    
    
    // Remove the binding when done
    webAppBinding.remove().get();
```
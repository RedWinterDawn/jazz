# REST Client

REST client allows you to wire up a dynamic proxy based (read automagic) implementation of for a 
REST service based on a JAX-RS annotated interface.  The proxy implementation performs all the 
mundane tasks involve in making a REST call to another service so that you can focus on other 
exciting subjects.

The general work flow for creating and using a REST client is as follows:

* Declare a Rest-annotated interface
* Create an dynamic implementation for it through a `RestClientFactory` and `RestClientBuilder`
* ???
* Profit

## Declare an Annotated Interface

Many of the annotations supported come directly from JAX-RS.  Where JAX-RS falls short, custom
annotations have been developed as part of the Jazz API in order to fill the gap.  Examine the 
API source code for full details of the available annotations. 

```java
    @Path("/")
    public interface RestInterface
    {
        // A simple GET request, defaulting to JSON as the payload.
        @GET
        @Path("hello")
        PnkyPromise<Greetings> getHello();
        
        // A multipart form upload (POST) with parts of different media types.
        @POST
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        @Path("/postWithMultipartFormParam")
        PnkyPromise<Void> postWithMultipartFormParam(
            @MultipartFormParam(value = "p1", mediaType = MediaType.APPLICATION_JSON) final String param,
            @MultipartFormParam(value = "p2", mediaType = MediaType.APPLICATION_OCTET_STREAM) final byte[] param2);
        
        // A GET request expecting JSON as the response format with 2 retry attempts and a limited
        // set of acceptable response codes.
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @RestResponseStrategy(
            expectedStatus = { 200 },
            maxRetries = 2,
            allowAll2xxResponseCodes = false)
        PnkyPromise<Greetings> getHello();
    }
```

Accepted annotations on the interface are many of the standard annotations from the `javax.ws.rs`
package - including `@GET, @POST, @Path` etc. A particularly noteworthy annotation is 
`@RestResponseStrategy` which allows customizing how the response is handled. These options are 
included in the annotation:

* `expectedStatus` is an array of `int` containing acceptable status(es) for this request. Any 
  response whose status matches an expected status will have its body deserialized **or attempted 
  to do so**. Any failure or otherwise unexpected statuses will result in the returned future 
  completing exceptionally Default value: `{}` (see `allowAll2xxResponseCodes` for why).
* `maxRetries` is an `int` that indicates how many times the client should reattempt the request, 
  after the initial attempt, in the event that a non-expected status is received or another internal
  error occurs. Extra care should be given when this option is used to make sure that repeating 
  the request does not cause any undesirable result.  If a filtering enable supplier is provided
  to the client during construction, the client will attempt to avoid already tried server(s) on 
  retry attempt. If that is not possible or if retries are exhausted, the client completes the
  returned future exceptionally.  The first retry attempt occurs 100ms after the failure, subsequent
  attempts will be spaced 1.5 times longer than the prior delay, up to a maximum of 5 seconds 
  between retry attempts.  Default value: `0`.
* `allowAll2xxResponseCodes` indicates whether to include all 2xx statuses in the `expectedStatus` 
  array or not. If set to `true` all 2xx status are accepted and hence the final expected status is 
  the union between `expectedStatus` and all 200s. Set this to `false` and explicitly provide the
  accepted 2xx codes if your application considers any 2xx status as failure. Default value: `true`.

### Support for Service Discovery

The REST clients returned from the factories created via the manager support dynamic discovery of
target endpoints when the discovery system is available in the runtime.

Interfaces annotated with the `JumpyService` or `JazzService` will be automatically configured 
with a URL supplier backed by the service registry.  If the service registry is unavailable, a
warning will be logged and construction of the client will eventually fail if a URL is not 
explicitly provided to the `RestClientBuilder` before invoking the `build` method.

## Configure a REST Client Factory via the REST Client Factory Manager

The `RestClientFactoryManager` provides a means to create, configure, and retrieve 
`RestClientFactory` instances without having to explicitly instantiate a concrete factory instance.
Factories may be created by constructing a `RestClientFactoryDescriptor` and calling the 
`addRestClientFactory` method of the manager.

```java

    final RestClientFactoryDescriptor descriptor = RestClientFactoryDescriptor.builder()
        .id("foo");
        .connectTimeout(500)
        .socketTimeout(5000)
        .httpProxy(HostAndPort.fromString("192.168.0.10:5000")
        .build();
              
    RestClientFactoryBinding binding = restClientFactoryManager
        .addRestClientFactory(descriptor)
        // Go synchronous for demo purposes.
        .get();
```

## Retrieve a Client Instance 

Use the "getX" methods of the manager to subsequently create a factory, builder, or client instance
using the ID of previously configured factories.

```java
    RestInterface restInterface = 
        restClientFactoryManager
            .getRestClientBuilder(
                RestClientFactoryManager.INTERNAL_REST_CLIENT_FACTORY_ID,
                RestInterface.class)
            .thenTransform((b) ->
            {
              return b
                  .addRestClientSerializer(new JacksonJsonRestClientSerializer(new ObjectMapper()))
                  .build();
            })
            // Use synchronously for demo purposes.
            .get();
```

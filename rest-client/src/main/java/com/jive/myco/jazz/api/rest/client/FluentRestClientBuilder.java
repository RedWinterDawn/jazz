package com.jive.myco.jazz.api.rest.client;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Supplier;

import javax.ws.rs.ext.ParamConverterProvider;

import com.jive.myco.jazz.api.registry.RegistryManager;
import com.jive.myco.jazz.api.registry.ServiceInstanceSupplier;
import com.jive.myco.jazz.api.web.HttpClientHeaderDecorator;
import com.jive.v5.jumpy.JumpyRecordFilterCriteria;
import com.jive.v5.jumpy.RestrictedSupplier;

/**
 * Describes fluent builder API for constructing a REST client. The {@code build()} method is
 * explicitly omitted to facilitate the use of this builder API as part of a larger DSL for
 * configuring applications.
 *
 * @author David Valeri
 *
 * @param <T>
 *          the narrowed fluent builder sub-type returned from the fluent API
 */
public interface FluentRestClientBuilder<T extends FluentRestClientBuilder<T>>
{
  /**
   * Adds a serializer to the configured serializers that are available to the client.
   *
   * @param mapper
   *          the serializer to add
   *
   * @return this builder for chaining
   */
  T addRestClientSerializer(final RestClientSerializer mapper);

  /**
   * Adds a listener to the listeners that are notified when an event happens on the constructed
   * client. The listener is invoked on a thread managed by the client.
   *
   * @param listener
   *          the listener to add
   *
   * @return this builder for chaining
   */
  T addListener(final RestClientListener listener);

  /**
   * Adds a listener to the listeners that are notified when an event happens on the constructed
   * client. The listener is invoked on the supplied {@code executor}.
   *
   * @param listener
   *          the listener to add
   * @param executor
   *          the executor used to execute the supplied listener
   *
   * @return this builder for chaining
   */
  T addListener(final RestClientListener listener, final Executor executor);

  /**
   * Adds a provider for parameter conversion.
   *
   * @param provider
   *          the provider
   *
   * @return this builder for chaining
   */
  T addProvider(final ParamConverterProvider provider);

  /**
   * Sets a fixed base URL to which all requests are directed.
   *
   * @param url
   *          the base URL
   *
   * @return this builder for chaining
   */
  T url(final String url);

  /**
   * Sets a dynamically calculated base URL to which requests are directed. The provided supplier is
   * invoked for each request.
   *
   * @param urlSupplier
   *          a supplier that provides a valid base URL on each invocation of {@link Supplier#get()}
   *
   * @return this builder for chaining
   */
  T url(final Supplier<String> urlSupplier);

  /**
   * Sets a dynamically calculated base URL based on the Jumpy service discovery API. The provided
   * supplier is invoked for each request with additional filter criteria as needed.
   *
   * @param urlRestrictedSupplier
   *          a supplier that provides a valid base URL on each invocation
   *          {@link RestrictedSupplier#getRestricted(Object)}.
   *
   * @return this builder for chaining
   *
   * @deprecated use {@link #url(ServiceInstanceSupplier)} instead
   */
  @Deprecated
  T url(
      final RestrictedSupplier<String, JumpyRecordFilterCriteria> urlRestrictedSupplier);

  /**
   * Sets a dynamically calculated base URL based on the {@link RegistryManager} service discovery
   * API. The provided supplier is invoked for each request with additional filter criteria as
   * needed.
   *
   * @param serviceInstanceSupplier
   *          a supplier that provides the best matching service instance on each invocation of
   *          {@link ServiceInstanceSupplier#get(com.jive.myco.jazz.api.registry.ServiceInstanceSupplierSelectorCriteria)}
   *          .
   *
   * @return this builder for chaining
   */
  T url(final ServiceInstanceSupplier serviceInstanceSupplier);

  /**
   * Sets the header decorator used by the client when making requests.
   *
   * @param headerDecorator
   *          the decorator to use
   *
   * @return this builder for chaining
   */
  T headerDecorator(final HttpClientHeaderDecorator headerDecorator);

  /**
   * Sets the scheduled executor to use for scheduling retry attempts.
   *
   * @param executor
   *          the executor to use
   *
   * @return this builder for chaining
   */
  T scheduledExecutor(final ScheduledExecutorService executor);
}

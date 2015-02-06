package com.jive.myco.jazz.api.rest.client;

import java.util.Optional;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.lifecycle.ListenableLifecycled;
import com.jive.myco.jazz.api.registry.JazzService;
import com.jive.myco.jazz.api.registry.RegistryManager;
import com.jive.v5.jumpy.JumpyService;

/**
 * A manager for {@link RestClientFactory}, {@link RestClientBuilder}, and REST client instances.
 * Each of the afore mentioned entities may be retrieved based on a unique ID that identifies the
 * general broad configuration for the factory, builder, or instance.
 *
 * @author David Valeri
 */
public interface RestClientFactoryManager extends ListenableLifecycled
{
  public static final String INTERNAL_REST_CLIENT_FACTORY_ID = "internal";
  public static final String EXTERNAL_REST_CLIENT_FACTORY_ID = "external";

  /**
   * Creates a new factory instance in the manager based on the configuration in the provided
   * descriptor.
   *
   * @param descriptor
   *          the descriptor containing configuration options for the added factory
   *
   * @return a future that completes with the binding representing a handle to the created factory
   */
  PnkyPromise<RestClientFactoryBinding> addRestClientFactory(
      final RestClientFactoryDescriptor descriptor);

  /**
   * Returns a {@link RestClientFactory} that may be used to configure and build a REST client.
   *
   * @param id
   *          the ID of the REST client factory to use
   *
   * @return a future that completes with the requested factory
   */
  PnkyPromise<Optional<RestClientFactory>> getFactory(final String id);

  /**
   * Returns a {@link RestClientBuilder} that may be used to configure and build a REST client.
   *
   * @param id
   *          the ID of the REST client factory to use
   * @param klass
   *          the interface to bind
   *
   * @return a future that completes with the requested builder
   */
  <T> PnkyPromise<RestClientBuilder<T>> getRestClientBuilder(final String id, final Class<T> klass);

  /**
   * Returns a REST client instance with pre-configured endpoint resolution via the
   * {@link RegistryManager} based on the presence of a {@link JumpyService} or {@link JazzService}
   * annotation.
   *
   * @param id
   *          the ID of the REST client factory to use
   * @param klass
   *          the interface to bind
   *
   * @return a future that completes with the fully initialized REST client instance
   */
  <T> PnkyPromise<T> getRestClient(final String id, final Class<T> klass);
}

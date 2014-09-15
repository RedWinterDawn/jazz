package com.jive.myco.jazz.api.rest.client;

/**
 * Defines the factory interface for creating REST client instances.
 *
 * @author David Valeri
 */
public interface RestClientFactory
{
  /**
   * Creates a new client instance builder for the supplied class.
   *
   * @param klass
   *          the interface to bind
   */
  public <T> RestClientBuilder<T> bind(final Class<T> klass);
}

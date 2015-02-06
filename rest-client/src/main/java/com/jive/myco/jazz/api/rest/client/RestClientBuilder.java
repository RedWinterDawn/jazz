package com.jive.myco.jazz.api.rest.client;


/**
 * Builder for a REST client.
 *
 * @author David Valeri
 * @author Binh Tran
 *
 * @param <T>
 *          the interface type for the returned client
 */
public interface RestClientBuilder<T> extends FluentRestClientBuilder<RestClientBuilder<T>>
{
  /**
   * Complete the builder and create the client.
   *
   * @return the fully initialized client
   */
  T build();
}

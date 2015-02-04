package com.jive.myco.jazz.api.rest.client;

import com.jive.myco.commons.concurrent.PnkyPromise;

/**
 * A handle to a {@link RestClientFactory} instance created via a {@link RestClientFactoryManager}.
 *
 * @author David Valeri
 */
public interface RestClientFactoryBinding
{
  /**
   * Removes the factory from the manager.
   *
   * @return a future that completes when the factory has been removed or an error occurs
   */
  PnkyPromise<Void> remove();
}

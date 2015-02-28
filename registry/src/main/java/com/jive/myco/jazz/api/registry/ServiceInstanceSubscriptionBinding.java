package com.jive.myco.jazz.api.registry;

import com.jive.myco.commons.concurrent.PnkyPromise;

/**
 * A binding representing the subscription of a listener to receive events about matching service
 * instances.
 *
 * @author David Valeri
 */
public interface ServiceInstanceSubscriptionBinding
{
  /**
   * Removes the binding if active.
   *
   * @return a promise that completes when the binding has been removed and the subscription is no
   *         longer active
   */
  PnkyPromise<Void> remove();
}

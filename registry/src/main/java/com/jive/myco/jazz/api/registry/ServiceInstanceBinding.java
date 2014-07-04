package com.jive.myco.jazz.api.registry;

import com.jive.myco.commons.concurrent.PnkyPromise;

/**
 * Represents information about a registered service instance and the actions that may be applied to
 * the instance.
 *
 * @see com.jive.myco.jazz.api.registry.ServiceInstance
 *
 * @author John Norton
 */
public interface ServiceInstanceBinding
{
  /**
   * Unregisters the service instance from the registry.
   *
   * @return a future that completes when the unregistration completes of an error occurs
   */
  PnkyPromise<Void> unRegister();

  /**
   * @return information about the registered service instance
   */
  ServiceInstance getServiceInstance();
}

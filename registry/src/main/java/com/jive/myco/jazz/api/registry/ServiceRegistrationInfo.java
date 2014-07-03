package com.jive.myco.jazz.api.registry;

import com.jive.myco.commons.concurrent.PnkyPromise;

/**
 * Interface to object that represents information about the registered service and method to unregister.
 * @see com.jive.myco.jazz.api.registry.ServiceInstance
 * @author John Norton
 */
public interface ServiceRegistrationInfo
{
  /**
   * Unregisters the service from the Registry
   * @return
   */
  PnkyPromise<Void> unRegister();

  /**
   *
   * @return Information about the registered service.
   */
  ServiceInstance getServiceInstance();
}

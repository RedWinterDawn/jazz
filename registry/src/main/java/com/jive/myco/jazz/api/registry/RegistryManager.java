package com.jive.myco.jazz.api.registry;

import java.util.Set;

import com.jive.myco.commons.concurrent.PnkyPromise;

/**
 * Used to register and lookup services in the service Registry.
 * 
 * @see com.jive.myco.commons.concurrent.PnkyPromise
 * @see com.jive.myco.jazz.api.registry.ServiceRegistrationInfo
 * @see com.jive.myco.jazz.api.registry.ServiceInstanceDescriptor
 * @author John Norton
 */

public interface RegistryManager
{
  /**
   * 
   * Registers a service
   * 
   * @param serviceInstanceDescriptor
   *          information about the service to be registered
   *          {@link com.jive.myco.jazz.api.registry.ServiceInstanceDescriptor}
   * 
   * @return future with information about the registered service
   * 
   * @throws com.jive.myco.jazz.api.registry.exceptions.FailedToRegisterServiceException
   *           part of future
   * 
   */
  PnkyPromise<ServiceRegistrationInfo> registerService(
      ServiceInstanceDescriptor serviceInstanceDescriptor);

  /**
   * Searches registry for the given interface. Services returned should be checked for locality and
   * health before use.
   * 
   * @param serviceInterface
   *          Represents search parameters {@link com.jive.myco.jazz.api.registry.ServiceInterface}
   * 
   * @return Collection (SET) of ServiceInstances found.
   *         {@link com.jive.myco.jazz.api.registry.ServiceInstance}
   */

  PnkyPromise<Set<ServiceInstance>> getServiceInstances(ServiceInterface serviceInterface);
}

package com.jive.myco.jazz.api.registry;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.lifecycle.ListenableLifecycled;
import com.jive.myco.jazz.api.registry.exceptions.RegistryManagerException;

/**
 * Used to register and lookup services in the service Registry.
 *
 * @see com.jive.myco.commons.concurrent.PnkyPromise
 * @see com.jive.myco.jazz.api.registry.RegisteredServiceInstanceBinding
 * @see com.jive.myco.jazz.api.registry.ServiceInstanceDescriptor
 *
 * @author John Norton
 */
public interface RegistryManager extends ListenableLifecycled
{
  /**
   * Registers a service instance.
   * <p>
   * NOTE: The exceptions declared to be thrown in this JavaDoc are actually used to complete the
   * returned future rather than being thrown.
   *
   * @param serviceInstanceDescriptor
   *          information about the service to be registered
   *
   * @return future with information about the registered service
   *
   * @throws RegistryManagerException
   *           if there is an error
   */
  PnkyPromise<RegisteredServiceInstanceBinding> registerService(
      final ServiceInstanceDescriptor serviceInstanceDescriptor);
}

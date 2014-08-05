package com.jive.myco.jazz.api.registry;

import java.util.Set;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.lifecycle.ListenableLifecycled;

/**
 * Used to register and lookup services in the service Registry.
 *
 * @see com.jive.myco.commons.concurrent.PnkyPromise
 * @see com.jive.myco.jazz.api.registry.ServiceInstanceBinding
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
   * @throws com.jive.myco.jazz.api.registry.exceptions.FailedToRegisterServiceException
   *           part of future
   */
  PnkyPromise<ServiceInstanceBinding> registerService(
      final ServiceInstanceDescriptor serviceInstanceDescriptor);

  /**
   * Searches the registry for registered service instances providing the given service interface.
   * The returned services are guaranteed to implement the major version of the supplied interface
   * but are not guaranteed to be healthy or available.
   * <p>
   * NOTE: The exceptions declared to be thrown in this JavaDoc are actually used to complete the
   * returned future rather than being thrown.
   *
   * @param serviceInterface
   *          represents search parameters {@link com.jive.myco.jazz.api.registry.ServiceInterface}
   *
   * @return a set of service interfaces providing the requested service interface
   */
  PnkyPromise<Set<ServiceInstance>> getServiceInstances(final ServiceInterface serviceInterface);
}

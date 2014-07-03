package com.jive.myco.jazz.api.registry;

import java.util.Set;

import com.jive.myco.commons.concurrent.PnkyPromise;

/**
 * @author John Norton
 */

public interface RegistryManager
{
  PnkyPromise<ServiceRegistrationInfo> registerService(
      ServiceInstanceDescriptor serviceInstanceDescriptor) throws Exception;

  PnkyPromise<Set<ServiceInstance>> getServiceInstances(ServiceInterface serviceInterface);
}

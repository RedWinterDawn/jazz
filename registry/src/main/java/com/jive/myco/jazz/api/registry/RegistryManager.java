package com.jive.myco.jazz.api.registry;

import java.util.Set;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.versions.Version;
import com.jive.myco.jazz.api.core.coordinates.Locality;

/**
 * @author John Norton
 */

public interface RegistryManager
{
  PnkyPromise<RegistrationInfo> registerService(ServiceAddress serviceAddresses, Version version,
      String interfaceName, String instanceId, Locality locality) throws Exception;

  PnkyPromise<Set<ServiceInstance>> lookupService(String interfaceName, Locality locality);
}

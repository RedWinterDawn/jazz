package com.jive.myco.jazz.api.registry;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.jazz.api.core.coordinates.Locality;

import java.util.List;

/**
 * Created by jnorton on 6/27/14.
 */
public interface RegistryService
{
  PnkyPromise<RegistrationInfo> registerService(ServiceAddress serviceAddresses, String version,
      String interfaceName, String instanceId, Locality locality) throws Exception;

  PnkyPromise<List<ServiceInstance>> lookupService(String interfaceName, Locality locality);
}

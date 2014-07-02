package com.jive.myco.jazz.api.registry;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.jazz.api.core.coordinates.Locality;
import lombok.Getter;

/**
 * @author John Norton
*/
public interface ServiceRegistrationInfo {
  PnkyPromise<Void> unRegister();
  ServiceInstanceDescriptor getServiceInstanceDescriptor();
}

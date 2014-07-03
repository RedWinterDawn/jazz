package com.jive.myco.jazz.api.registry;

import com.jive.myco.commons.concurrent.PnkyPromise;

/**
 * @author John Norton
*/
public interface ServiceRegistrationInfo {
  PnkyPromise<Void> unRegister();
  ServiceInstanceDescriptor getServiceInstanceDescriptor();
}

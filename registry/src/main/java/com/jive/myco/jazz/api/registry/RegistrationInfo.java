package com.jive.myco.jazz.api.registry;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.jazz.api.core.coordinates.Locality;

/**
 * @author John Norton
*/

public interface RegistrationInfo {
  PnkyPromise<Void> unRegister();
  String getGroupId();
  Locality getLocality();
  boolean isLeader();
  String getServiceHealth();
  String getLifecycle();
}

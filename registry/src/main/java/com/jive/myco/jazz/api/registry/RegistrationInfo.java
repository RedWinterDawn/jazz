package com.jive.myco.jazz.api.registry;

import com.jive.myco.commons.concurrent.PnkyPromise;

/**
 * Created by jnorton on 7/1/14.
 */


public interface RegistrationInfo {
  public PnkyPromise<Void> unRegister();
}

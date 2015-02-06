package com.jive.myco.jazz.api.registry;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.jazz.api.core.ConnectedBindingGracefulShutdownHook;

/**
 * A shutdown hook for items that are bound to some manager while also being registered in the
 * service registry and requiring a means to control the process of removing the item from the
 * manager and registry.
 *
 * The methods of this interface will be invoked in the following order:
 * {@link #removeRegistrationRequested()}, {@link #removeRequested()}.
 *
 * @author David Valeri
 */
public interface ConnectedAndRegisteredBindingGracefulShutdownHook extends
    ConnectedBindingGracefulShutdownHook
{
  /**
   * Called at the point in time when the connected and registered binding would like to unregister
   * from from the service registry. Implementations of this method must not perform synchronous
   * blocking activities as the calling thread is not guaranteed to support such actions.
   *
   * @return a promise that completes when the connected and registered binding may proceed with the
   *         removal from the registry
   *
   * @see RegisteredServiceInstanceBinding#remove()
   */
  PnkyPromise<Void> removeRegistrationRequested();
}

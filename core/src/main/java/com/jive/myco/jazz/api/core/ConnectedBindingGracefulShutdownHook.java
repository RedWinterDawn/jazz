package com.jive.myco.jazz.api.core;

import com.jive.myco.commons.concurrent.PnkyPromise;

/**
 * A shutdown hook for items that are bound to some manager and require a means to control the
 * process of removing the item from the manager.
 *
 * @author David Valeri
 */
public interface ConnectedBindingGracefulShutdownHook
{
  /**
   * Called at the point in time when the binding would like to be removed completely.
   * Implementations of this method must not perform synchronous blocking activities as the calling
   * thread is not guaranteed to support such actions.
   *
   * @return a promise that completes when the connected binding may proceed with final removal of
   *         the binding
   * 
   * @see Binding#remove()
   */
  PnkyPromise<Void> removeRequested();
}

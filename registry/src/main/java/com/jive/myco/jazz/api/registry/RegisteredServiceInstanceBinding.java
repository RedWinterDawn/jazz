package com.jive.myco.jazz.api.registry;

import java.util.Map;

import com.jive.myco.commons.concurrent.PnkyPromise;

/**
 * Represents information about a registered service instance and the actions that may be applied to
 * the instance.
 *
 * @author John Norton
 */
public interface RegisteredServiceInstanceBinding
{
  /**
   * Unregisters the service instance from the registry.
   *
   * @deprecated signature is changing, use removeAsync in the interim
   */
  @Deprecated
  void remove();

  /**
   * Unregisters the service instance from the registry.
   */
  PnkyPromise<Void> removeAsync();

  /**
   * Updates the service instance data in the registry.
   *
   * @param properties
   *          the new properties to assign
   */
  void update(final Map<String, String> properties);
}

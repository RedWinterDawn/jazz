package com.jive.myco.jazz.api.registry;

import java.util.Map;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.jazz.api.core.Binding;

/**
 * Represents information about a registered service instance and the actions that may be applied to
 * the instance.
 *
 * @author John Norton
 */
public interface RegisteredServiceInstanceBinding extends Binding<Void>
{
  /**
   * Unregisters the service instance from the registry.
   *
   * @deprecated used to bridge API changes. Use {@link #remove()} instead.
   */
  @Deprecated
  PnkyPromise<Void> removeAsync();

  /**
   * Updates the service instance data in the registry.
   *
   * @param properties
   *          the new properties to assign
   */
  void update(final Map<String, String> properties);

  /**
   * Unregisters the service instance from the registry.
   */
  @Override
  PnkyPromise<Void> remove();
}

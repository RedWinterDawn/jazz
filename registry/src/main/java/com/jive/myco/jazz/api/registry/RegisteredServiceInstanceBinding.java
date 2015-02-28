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
   * Updates the service instance data in the registry.
   *
   * @param properties
   *          the new properties to assign
   *
   * @deprecated use {@link #updateAsync(Map) instead. This method will be replaced by the async
   *             signature in a future release.
   */
  @Deprecated
  void update(final Map<String, String> properties);

  /**
   * Updates the service instance data in the registry.
   *
   * @param properties
   *          the new properties to assign
   */
  PnkyPromise<Void> updateAsync(final Map<String, String> properties);

  /**
   * Unregisters the service instance from the registry.
   */
  @Override
  PnkyPromise<Void> remove();
}

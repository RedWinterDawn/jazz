package com.jive.myco.jazz.api.registry;

import java.util.Optional;

/**
 * An interface describing a supplier of {@link ServiceInstance} instances.
 *
 * @author David Valeri
 */
public interface ServiceInstanceSupplier
{
  /**
   * Returns a service instance bearing optional containing a service instance that matches the
   * initial criteria supplied when the supplier was created and the additional per-request criteria
   * supplied to this method.
   *
   * @param filterCriteria
   *
   * @return an optional containing an available matching service instance if available
   *
   * @throws IllegalStateException
   *           if the backing registry manager is not initialized
   */
  Optional<ServiceInstance> get(
      final ServiceInstanceSupplierSelectorCriteria criteria);
}

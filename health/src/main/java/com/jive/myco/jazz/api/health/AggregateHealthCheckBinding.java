package com.jive.myco.jazz.api.health;

import com.jive.myco.commons.concurrent.PnkyPromise;

/**
 * Represents the binding of an {@link AggregateHealthCheck} into the {@link HealthCheckManager}.
 *
 * @author David Valeri
 */
public interface AggregateHealthCheckBinding extends AggregateHealthCheck
{
  /**
   * Removes the check from the manager.
   *
   * @return a future that completes when the check has been removed or an error ocurrs
   */
  PnkyPromise<Void> remove();
}

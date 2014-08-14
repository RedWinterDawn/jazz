package com.jive.myco.jazz.api.health;

import java.util.Set;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.lifecycle.Lifecycled;
import com.jive.myco.commons.lifecycle.ListenableLifecycled;

/**
 * A manager for orchestrating the creation of various health check components.
 *
 * @author David Valeri
 */
public interface HealthCheckManager extends Lifecycled, ListenableLifecycled
{
  /**
   * Creates a new {@link AggregateHealthCheck} based on the supplied descriptor.
   *
   * @param descriptor
   *          the descriptor to process
   *
   * @return a future that completes when the aggregate health check has been created or an error
   *         occurs
   */
  PnkyPromise<AggregateHealthCheckBinding> addAggregateHealthCheck(
      final AggregateHealthCheckDescriptor desrciptor);

  /**
   * Returns the aggregate health check for the given ID.
   *
   * @param id
   *          the ID of the aggregate health check to retrieve
   *
   * @return the corresponding aggregate health check or {@code null} if no such check exists
   */
  AggregateHealthCheck getAggregateHealthCheck(final String id);

  /**
   * Returns a snapshot of the currently registered aggregate health checks.
   */
  Set<AggregateHealthCheck> getAggregateHealthChecks();

  /**
   * Adds a {@link AggregateHealthCheckListener} to the manager that will be added to all
   * {@link AggregateHealthCheck}s
   * 
   * @param listener
   *          the listener to add to the manager
   */
  PnkyPromise<Void> addDefaultAggregateHealthCheckListener(
      final AggregateHealthCheckListener listener);
}

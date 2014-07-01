package com.jive.myco.jazz.api.health;

import java.util.Set;
import java.util.concurrent.Executor;

import com.jive.myco.commons.concurrent.PnkyPromise;

/**
 * Represents an aggregation of {@link HealthCheck}s. The {@link #getHealthStatus() aggregate health
 * status} is the least healthy status of this aggregate check's constituent
 * {@link #getHealthChecks()}. Health statuses, ordered from least healthy to most healthy are
 * {@link HealthStatus#UNHEALTHY}, {@link HealthStatus#UNKNOWN}, {@link HealthStatus#HEALTHY}.
 * <p>
 * An aggregate health check may be obtained via the {@link HealthCheckManager}.
 *
 * @author David Valeri
 */
public interface AggregateHealthCheck extends HealthStatusable
{
  /**
   * Returns the unique identifier of this check.
   */
  String getId();

  /**
   * Returns a snapshot of the constituent checks that comprise this aggregate check.
   */
  Set<HealthCheck> getHealthChecks();

  /**
   * Adds an additional health check to this aggregate check, updating this check's status as
   * appropriate. Adding a health check that has previously been added has no effect.
   *
   * @param healthCheck
   *          the health check to add
   *
   * @return a future that completes when the new check has been added or an error occurs
   */
  PnkyPromise<Void> addHealthCheck(final HealthCheck healthCheck);

  /**
   * Removes a previously registered health check from this aggregate check. Removing a check that
   * has not been {@link #addHealthCheck(HealthCheck) added} has no effect.
   *
   * @param healthCheck
   *          the check to remove
   *
   * @return a future that completes when the check has been removed or an error occurs
   */
  PnkyPromise<Void> removeHealthCheck(final HealthCheck healthCheck);

  /**
   * Adds a new listener to the check. The listener is invoked on a thread managed by this health
   * check instance. Upon being added, the listener is immediately notified of the check's current
   * status. Adding a listener that has already been added reconfigures the listener's assigned
   * executor.
   *
   * @param listener
   *          the listener to add
   */
  PnkyPromise<Void> addListener(final AggregateHealthCheckListener listener);

  /**
   * Adds a new listener to the check. The listener is invoked on the provided {@code executor}.
   * Upon being added, the listener is immediately notified of the check's current status. Adding a
   * listener that has already been added reconfigures the listener's assigned executor.
   *
   * @param listener
   *          the listener to add
   * @param executor
   *          the executor to invoke the listener on
   */
  PnkyPromise<Void> addListener(final AggregateHealthCheckListener listener, final Executor executor);

  /**
   * Adds a listener from the check. Removing a listener that has not previously been
   * {@link #addListener(HealthCheckListener) added} has no effect.
   *
   * @param listener
   *          the listener to remove
   */
  PnkyPromise<Void> removeListener(final AggregateHealthCheckListener listener);
}

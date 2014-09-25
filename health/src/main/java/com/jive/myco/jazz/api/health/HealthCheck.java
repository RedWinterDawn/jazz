package com.jive.myco.jazz.api.health;

import java.util.concurrent.Executor;

/**
 * Provides a view into a resource of that resource's health status. Implementations are expected to
 * be thread safe.
 *
 * @author David Valeri
 */
public interface HealthCheck extends HealthStatusable
{
  /**
   * Returns the identifier for this check.
   */
  String getId();

  /**
   * Adds a new listener to the check. The listener is invoked on a thread managed by this health
   * check instance. Upon being added, the listener is immediately notified of the check's current
   * status. Adding a listener that has already been added reconfigures the listener's assigned
   * executor.
   *
   * @param listener
   *          the listener to add
   */
  void addListener(final HealthCheckListener listener);

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
  void addListener(final HealthCheckListener listener, final Executor executor);

  /**
   * Adds a listener from the check. Removing a listener that has not previously been
   * {@link #addListener(HealthCheckListener) added} has no effect.
   *
   * @param listener
   *          the listener to remove
   */
  void removeListener(final Object listener);
}

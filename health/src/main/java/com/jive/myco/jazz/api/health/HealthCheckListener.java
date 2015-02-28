package com.jive.myco.jazz.api.health;


/**
 * A listener for updates in status of a {@link HealthCheck}.
 *
 * @author David Valeri
 */
public interface HealthCheckListener
{
  /**
   * Triggers on the change of the health status of {@code healthCheck} or on initial addition to a
   * {@link HealthCheck}.
   *
   * @param healthCheck
   *          the check that experienced the status change
   * @param newHealthStatusAndMessage
   *          the new status and optional message
   *
   * @see HealthCheck#addListener(HealthCheckListener)
   * @see HealthCheck#addListener(HealthCheckListener, java.util.concurrent.Executor)
   */
  void onHealthCheckStatusChanged(final HealthCheck healthCheck,
      final HealthStatusAndMessage newHealthStatusAndMessage);
}

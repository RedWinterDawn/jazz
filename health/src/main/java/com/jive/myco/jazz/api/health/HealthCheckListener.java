package com.jive.myco.jazz.api.health;


/**
 * A listener for updates in status of a {@link HealthCheck}. Listener implementations should expect
 * the possibility of concurrent updates and must provide their own means of synchronization if
 * ordering of notification processing is important.
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
   * @param status
   *          the new status
   *
   * @see HealthCheck#addListener(HealthCheckListener)
   * @see HealthCheck#addListener(HealthCheckListener, java.util.concurrent.Executor)
   */
  void onHealthCheckStatusChanged(final HealthCheck healthCheck, final HealthStatus status);
}

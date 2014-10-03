package com.jive.myco.jazz.api.health;

import java.util.Map;


/**
 * A listener for updates in status of a {@link HealthCheck}. Listener implementations should expect
 * the possibility of concurrent updates and must provide their own means of synchronization if
 * ordering of notification processing is important.
 *
 * @author David Valeri
 */
public interface AggregateHealthCheckListener
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
   *
   * @deprecated use {@link #onHealthCheckStatusChanged(AggregateHealthCheck, HealthStatus, Map)}
   *             instead.
   */
  @Deprecated
  void onHealthCheckStatusChanged(final AggregateHealthCheck healthCheck,
      final HealthStatus status);

  /**
   * Triggers on the change of the health status of {@code healthCheck} or on initial addition to a
   * {@link HealthCheck}.
   * <p/>
   * The default implementation delegates to
   * {@link #onHealthCheckStatusChanged(AggregateHealthCheck, HealthStatus)}.
   *
   * @param healthCheck
   *          the check that experienced the status change
   * @param status
   *          the new status
   * @param healthCheckStatuses
   *          the statuses of each constituent check at the moment the listener triggers
   *
   * @see HealthCheck#addListener(HealthCheckListener)
   * @see HealthCheck#addListener(HealthCheckListener, java.util.concurrent.Executor)
   */
  default void onHealthCheckStatusChanged(final AggregateHealthCheck healthCheck,
      final HealthStatus status, final Map<HealthCheck, HealthStatusAndMessage> healthCheckStatuses)
  {
    onHealthCheckStatusChanged(healthCheck, status);
  }
}

package com.jive.myco.jazz.api.health;

import java.util.Map;

/**
 * A listener for updates to an {@link AggregateHealthCheck}. Listener implementations should expect
 * the possibility of concurrent updates and must provide their own means of synchronization if
 * ordering of notification processing is important when registered to multiple aggregate health
 * checks or adding to a health check with a user supplied executor.
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
   * @param healthCheckStatuses
   *          the statuses of each constituent check at the moment the listener triggers
   *
   * @see HealthCheck#addListener(HealthCheckListener)
   * @see HealthCheck#addListener(HealthCheckListener, java.util.concurrent.Executor)
   */
  void onHealthCheckStatusChanged(final AggregateHealthCheck healthCheck,
      final HealthStatus status, final Map<HealthCheck, HealthStatusAndMessage> healthCheckStatuses);

  /**
   * Triggers on the addition of {@code healthCheck} to {@code aggregateHealthCheck}.
   * <p/>
   * The default implementation performs a No-op.
   *
   * @param aggregateHealthCheck
   *          the aggregate health check that {@code healthCheck} was added to
   * @param healthCheck
   *          the check that was added
   */
  default void onHealthCheckAdded(final AggregateHealthCheck aggregateHealthCheck,
      final HealthCheck healthCheck)
  {
    // No-Op
  }

  /**
   * Triggers on the removal of {@code healthCheck} from {@code aggregateHealthCheck}.
   * <p/>
   * The default implementation performs a No-op.
   *
   * @param aggregateHealthCheck
   *          the aggregate health check that {@code healthCheck} was removed from
   * @param healthCheck
   *          the check that was removed
   */
  default void onHealthCheckRemoved(final AggregateHealthCheck aggregateHealthCheck,
      final HealthCheck healthCheck)
  {
    // No-Op
  }
}

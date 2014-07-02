package com.jive.myco.jazz.api.health;

/**
 * Defines a health check that must be prompted to update its health status by an external source.
 *
 * @author David Valeri
 */
public interface PolledHealthCheck extends HealthCheck
{
  /**
   * Recalculates this check's current health status and triggers listeners as required.
   */
  void updateHealthStatus();
}

package com.jive.myco.jazz.api.health;


/**
 * The base interface for anything that can report a health status.
 *
 * @author David Valeri
 */
public interface HealthStatusable
{
  /**
   * Returns the health status of this object.
   */
  HealthStatus getHealthStatus();
}

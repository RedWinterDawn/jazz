package com.jive.myco.jazz.api.health;

/**
 * An enumeration of possible health status indicators for a health monitored resource.
 *
 * @author David Valeri
 */
public enum HealthStatus
{
  /**
   * Indicates that the resource's health status is unhealthy. The resource may not perform
   * optimally or correctly.
   */
  UNHEALTHY,

  /**
   * Indicates that the resource's health status is unknown. The resource may not perform optimally
   * or correctly.
   */
  UNKNOWN,

  /**
   * Indicates that the resource's health statis is healthy. The resource can be expected to perform
   * optimally and correctly.
   */
  HEALTHY;
}

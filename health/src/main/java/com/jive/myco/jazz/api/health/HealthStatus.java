package com.jive.myco.jazz.api.health;

/**
 * An enumeration of possible health status indicators for a health monitored resource.
 * <p>
 * NOTE: The ordering of the values defined in this enumeration is significant. The enumeration
 * values must be defined with values representing more sever statuses appearing before values
 * representing less severe statuses.
 *
 * @author David Valeri
 */
public enum HealthStatus
{
  /**
   * Indicates that the resource's health status is critical. The resource is having major issues
   * that need to be looked into.
   */
  CRITICAL,

  /**
   * Indicates that the resource's health status is unknown. The resource may not perform optimally
   * or correctly.
   */
  UNKNOWN,

  /**
   * Indicates that the resource's health status is warn. The resource may not perform optimally or
   * correctly.
   */
  WARN,

  /**
   * Indicates that the resource's health status is info. The resource can be expected to perform
   * well but there is information that needs to be shared.
   */
  INFO,

  /**
   * Indicates that the resource's health status is healthy. The resource can be expected to perform
   * optimally and correctly.
   */
  OK;
}

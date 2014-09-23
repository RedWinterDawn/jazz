package com.jive.myco.jazz.api.health;

/**
 * An enumeration of possible health status indicators for a health monitored resource.
 *
 * @author David Valeri
 */
public enum HealthStatus
{
  /**
   * Indicates that the resource's health status is unknown. The resource may not perform optimally
   * or correctly.
   */
  UNKNOWN(-1),

  /**
   * Indicates that the resource's health status is healthy. The resource can be expected to perform
   * optimally and correctly.
   */
  OK(0),

  /**
   * Indicates that the resource's health status is info. The resource can be expected to perform
   * well but there is information that needs to be shared.
   */
  INFO(1),

  /**
   * Indicates that the resource's health status is warn. The resource may not perform optimally or
   * correctly.
   */
  WARN(2),

  /**
   * Indicates that the resource's health status is critical. The resource is having major issues
   * that need to be looked into.
   */
  CRITICAL(3);
  private int value;

  private HealthStatus(final int value)
  {
    this.value = value;
  }

  public int getValue()
  {
    return value;
  }
}

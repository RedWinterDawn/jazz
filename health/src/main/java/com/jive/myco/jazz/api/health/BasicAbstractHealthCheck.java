package com.jive.myco.jazz.api.health;

/**
 * A basic health check base class that provides on-demand status calculations. Subclasses should
 * implement {@link #calculateHealthStatus()} to return the current status on-demand.
 *
 * @author David Valeri
 */
public abstract class BasicAbstractHealthCheck extends AbstractHealthCheck implements
    PolledHealthCheck
{
  public BasicAbstractHealthCheck(final String id)
  {
    super(id);
  }

  @Override
  public final void updateHealthStatus()
  {
    setHealthStatus(calculateHealthStatus());
  }

  /**
   * Called from {@link #updateHealthStatus()} to calculate a new value for this check's current
   * status.
   *
   * @return the new status for the check
   */
  protected abstract HealthStatus calculateHealthStatus();
}

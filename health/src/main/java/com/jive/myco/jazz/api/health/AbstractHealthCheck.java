package com.jive.myco.jazz.api.health;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import com.jive.myco.commons.listenable.DefaultListenableContainer;

/**
 * A base class for various types of health checks. Sub classes may provide health updates on-demand
 * or provide cached results that are updated via an external trigger.
 *
 * @author David Valeri
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractHealthCheck implements HealthCheck
{
  private final DefaultListenableContainer<HealthCheckListener> listenableContainer =
      new DefaultListenableContainer<>();

  @NonNull
  private final String id;

  private final AtomicReference<HealthStatus> healthStatus = new AtomicReference<>(
      HealthStatus.UNKNOWN);

  @Override
  public final String getId()
  {
    return id;
  }

  @Override
  public final void addListener(final HealthCheckListener listener)
  {
    listenableContainer.addListenerWithInitialAction(
        listener,
        (Consumer<HealthCheckListener>) (daListener) -> onHealthStatusChanged(daListener,
            getHealthStatus()));
  }

  @Override
  public final void addListener(final HealthCheckListener listener, final Executor executor)
  {
    listenableContainer.addListenerWithInitialAction(
        listener,
        executor,
        (daListener) -> onHealthStatusChanged(daListener, getHealthStatus()));
  }

  @Override
  public final void removeListener(final Object listener)
  {
    listenableContainer.removeListener(listener);
  }

  @Override
  public final HealthStatus getHealthStatus()
  {
    return healthStatus.get();
  }

  /**
   * Sets the current health status value for this check, triggering registered listeners if the new
   * health status differs from the current value.
   *
   * @param newHealthStatus
   *          the new value for the health status of this check
   */
  protected final void setHealthStatus(@NonNull final HealthStatus newHealthStatus)
  {
    boolean success = false;

    while (!success)
    {
      final HealthStatus oldHealthStatus = healthStatus.get();

      if (newHealthStatus != oldHealthStatus)
      {
        if (healthStatus.compareAndSet(oldHealthStatus, newHealthStatus))
        {
          listenableContainer
              .forEach(((listener) -> onHealthStatusChanged(listener, newHealthStatus)));

          success = true;
        }
      }
      else
      {
        success = true;
      }
    }

  }

  private void onHealthStatusChanged(final HealthCheckListener listener,
      final HealthStatus newHealthStatus)
  {
    listener.onHealthCheckStatusChanged(this, newHealthStatus);
  }
}

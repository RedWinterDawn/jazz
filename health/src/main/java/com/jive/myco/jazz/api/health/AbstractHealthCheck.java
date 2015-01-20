package com.jive.myco.jazz.api.health;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.jive.myco.commons.listenable.DefaultListenableContainer;

/**
 * A base class for various types of health checks. Sub classes may provide health updates on-demand
 * or provide cached results that are updated via an external trigger.
 *
 * @author David Valeri
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public abstract class AbstractHealthCheck implements HealthCheck
{
  private final DefaultListenableContainer<HealthCheckListener> listenableContainer =
      new DefaultListenableContainer<>();

  @NonNull
  private final String id;

  private final AtomicReference<HealthStatus> healthStatus = new AtomicReference<>(
      HealthStatus.UNKNOWN);

  private volatile HealthStatusAndMessage healthStatusAndMessage = new HealthStatusAndMessage(
      healthStatus.get());

  @Override
  public final String getId()
  {
    return id;
  }

  @Override
  public HealthStatusAndMessage getHealthStatusAndMessage()
  {
    return healthStatusAndMessage;
  }

  @Override
  public final void addListener(final HealthCheckListener listener)
  {
    listenableContainer.addListenerWithInitialAction(
        listener,
        (Consumer<HealthCheckListener>) (daListener) -> onHealthStatusChanged(daListener,
            healthStatusAndMessage));
  }

  @Override
  public final void addListener(final HealthCheckListener listener, final Executor executor)
  {
    listenableContainer.addListenerWithInitialAction(
        listener,
        executor,
        (daListener) -> onHealthStatusChanged(daListener, healthStatusAndMessage));
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
   * Sets the current health status value, message value, and triggers listeners for this check, if
   * the new health status differs from the current value.
   *
   * @param newHealthStatusAndMessage
   *          the new values for the health status and message of this check
   *
   *
   */
  protected final void setHealthStatus(
      @NonNull final HealthStatusAndMessage newHealthStatusAndMessage)
  {
    boolean success = false;

    final HealthStatus newHealthStatus = newHealthStatusAndMessage.getHealthStatus();

    while (!success)
    {
      final HealthStatus oldHealthStatus = healthStatus.get();

      if (newHealthStatus != oldHealthStatus)
      {
        if (healthStatus.compareAndSet(oldHealthStatus, newHealthStatus))
        {
          log.debug("[{}]: Changing health status to [{}].", id, newHealthStatusAndMessage);

          healthStatusAndMessage = newHealthStatusAndMessage;

          listenableContainer
              .forEach(((listener) -> onHealthStatusChanged(listener, newHealthStatusAndMessage)));

          success = true;
        }
      }
      else
      {
        success = true;
      }
    }
  }

  /**
   * Sets the current health status value, an empty message, and triggers listeners for this check,
   * if the new health status differs from the current value.
   *
   * @param newHealthStatus
   *          the new value for the health status of this check
   *
   * @deprecated use {@link #setHealthStatus(HealthStatusAndMessage)} instead
   */
  @Deprecated
  protected final void setHealthStatus(@NonNull final HealthStatus newHealthStatus)
  {
    setHealthStatus(new HealthStatusAndMessage(newHealthStatus, null));
  }

  private void onHealthStatusChanged(final HealthCheckListener listener,
      final HealthStatusAndMessage newHealthStatusAndMessage)
  {
    listener.onHealthCheckStatusChanged(this, newHealthStatusAndMessage);
  }
}

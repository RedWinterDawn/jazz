package com.jive.myco.jazz.api.health;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Builder;

/**
 * A container that combines a health status and an optional message.
 *
 * @author David Valeri
 */
@AllArgsConstructor
@Builder
@Getter
@ToString
public final class HealthStatusAndMessage implements Comparable<HealthStatusAndMessage>
{
  @NonNull
  private final HealthStatus healthStatus;
  private final String message;

  public HealthStatusAndMessage(final HealthStatus healthStatus)
  {
    this(healthStatus, null);
  }

  @Override
  public int compareTo(final HealthStatusAndMessage o)
  {
    return healthStatus.compareTo(o.getHealthStatus());
  }
}
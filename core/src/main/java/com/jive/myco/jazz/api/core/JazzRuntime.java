package com.jive.myco.jazz.api.core;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Builder;

import com.jive.myco.jazz.api.core.coordinates.Coordinates;
import com.jive.myco.jazz.api.core.coordinates.InstanceTypeId;
import com.jive.myco.jazz.api.core.network.Networks;

/**
 * Represents information about the Jazz runtime environment.
 *
 * @author David Valeri
 */
@Builder
@Getter
public class JazzRuntime
{
  /**
   * The coordinates for this environment.
   */
  @NonNull
  private final Coordinates coordinates;

  /**
   * The name of the Jazz Service executing in this environment.
   */
  @NonNull
  private final String serviceName;

  /**
   * The instance type of this instance.
   */
  @NonNull
  private final InstanceTypeId instanceType;

  /**
   * The network information for this environment.
   */
  @NonNull
  private final Networks networks;

  /**
   * The environment ID for this environment.
   */
  @NonNull
  private final EnvironmentId environmentId;
}

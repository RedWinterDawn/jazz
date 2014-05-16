package com.jive.myco.jazz.core;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Builder;

import com.jive.myco.jazz.core.coordinates.Coordinates;

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
}

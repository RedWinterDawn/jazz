package com.jive.myco.jazz.api.registry;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * Represents the unique ID of a service instance in the registry.
 * <p>
 * Valid characters include a-z, A-Z, 0-9, period(.), dash(-), and underscore(_).
 *
 * @author John Norton
 */
@EqualsAndHashCode
public final class ServiceInstanceId
{
  private static String SERVICE_INSTANCE_ID_PATTERN = "^[\\w.-]+$";

  private final String serviceInstanceId;

  private ServiceInstanceId(@NonNull final String value)
  {
    this.serviceInstanceId = value;
  }

  public static ServiceInstanceId valueOf(final @NonNull String value)
  {
    if (value.matches(SERVICE_INSTANCE_ID_PATTERN))
    {
      return new ServiceInstanceId(value);
    }
    throw new IllegalArgumentException(
        String.format(
            "Failed to parse value of %s for class ServiceInstanceId.  Acceptable pattern is %s",
            value,
            SERVICE_INSTANCE_ID_PATTERN));
  }


  @Override
  public String toString()
  {
    return this.serviceInstanceId;
  }
}

package com.jive.myco.jazz.api.registry;

import lombok.NonNull;

/**
 * @author John Norton
 */

public class ServiceInstanceId
{

  @NonNull
  private final String serviceInstanceId;

  private static String SERVICE_INSTANCE_ID_PATTERN = "^[a-z_\\-\\.0-9]+$";

  private ServiceInstanceId(final String value)
  {
    this.serviceInstanceId = value;
  }

  public static ServiceInstanceId valueOf(final String value)
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

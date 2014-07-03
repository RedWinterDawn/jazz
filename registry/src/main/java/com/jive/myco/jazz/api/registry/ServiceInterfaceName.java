package com.jive.myco.jazz.api.registry;

import lombok.NonNull;

/**
 * Represents an Name of a service in the registry.<p>
 *
 * Valid characters include a-z, 0-9 period(.) dash(-) and underscore(_).
 *
 * @author John Norton
 */

public class ServiceInterfaceName
{

  private static String SERVICE_INTERFACE_NAME_PATTERN = "^[a-z_\\-\\.0-9]+$";

  @NonNull
  private final String serviceInterfaceName;

  private ServiceInterfaceName(final String serviceInterfaceName)
  {
    this.serviceInterfaceName = serviceInterfaceName;
  }

  public static ServiceInterfaceName valueOf(final String value)
  {
    if (value.matches(SERVICE_INTERFACE_NAME_PATTERN))
    {
      return new ServiceInterfaceName(value);
    }
    throw new IllegalArgumentException(
        String.format(
            "Failed to parse value for class ServiceInterfaceName.  Acceptable pattern is %s",
            SERVICE_INTERFACE_NAME_PATTERN));
  }

  @Override
  public String toString()
  {
    return serviceInterfaceName;
  }
}

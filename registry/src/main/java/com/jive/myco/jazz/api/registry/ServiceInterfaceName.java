package com.jive.myco.jazz.api.registry;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * Represents the name of a service interface in the registry.
 * <p>
 * Valid characters include a-z, 0-9 period(.) dash(-) and underscore(_).
 *
 * @author John Norton
 */
@EqualsAndHashCode
public final class ServiceInterfaceName
{
  private static String SERVICE_INTERFACE_NAME_PATTERN = "^[a-z_\\-\\.0-9]+$";

  private final String serviceInterfaceName;

  private ServiceInterfaceName(@NonNull final String serviceInterfaceName)
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

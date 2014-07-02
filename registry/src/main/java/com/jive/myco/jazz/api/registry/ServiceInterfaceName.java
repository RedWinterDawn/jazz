package com.jive.myco.jazz.api.registry;

import com.jive.myco.jazz.api.registry.exceptions.RegistryValueParseException;
import lombok.NonNull;

/**
 * @author John Norton
 */

public class ServiceInterfaceName
{

  private static String SERVICE_INTERFACE_NAME_PATTERN = "^[a-z_\\-\\.0-9]*$";

  @NonNull
  private final String serviceInterfaceName;

  private ServiceInterfaceName(String serviceInterfaceName)
  {
    this.serviceInterfaceName = serviceInterfaceName;
  }

  public static ServiceInterfaceName valueOf(String value) throws RegistryValueParseException
  {
    if (value.matches(SERVICE_INTERFACE_NAME_PATTERN))
    {
      return new ServiceInterfaceName(value);
    }
    throw new RegistryValueParseException(
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

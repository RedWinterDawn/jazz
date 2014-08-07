package com.jive.myco.jazz.api.registry;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * Represents the name of a service interface in the registry.
 *
 * @author John Norton
 */
@EqualsAndHashCode
public final class ServiceInterfaceName
{
  private final String serviceInterfaceName;

  private ServiceInterfaceName(@NonNull final String serviceInterfaceName)
  {
    this.serviceInterfaceName = serviceInterfaceName;
  }

  public static ServiceInterfaceName valueOf(final String value)
  {
    return new ServiceInterfaceName(value);
  }

  @Override
  public String toString()
  {
    return serviceInterfaceName;
  }
}

package com.jive.myco.jazz.api.registry;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * Represents an address of the service. Any string valid except null.
 *
 * @author John Norton
 */
@EqualsAndHashCode
public final class ServiceAddress
{
  private final String address;

  private ServiceAddress(final String address)
  {
    this.address = address;

  }

  @Override
  public String toString()
  {
    return address;
  }

  public static ServiceAddress valueOf(final @NonNull String value)
  {
    return new ServiceAddress(value);
  }
}

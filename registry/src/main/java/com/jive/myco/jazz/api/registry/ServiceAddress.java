package com.jive.myco.jazz.api.registry;


/**
 * @author John Norton
 */

public class ServiceAddress
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

  public static ServiceAddress valueOf(final String value)
  {
    return new ServiceAddress(value);
 }
}

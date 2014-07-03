package com.jive.myco.jazz.api.registry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;
import lombok.NonNull;

/**
 *
 * Represents an address of the service; Any string valid except null;
 * 
 * @author John Norton
 */

public class ServiceAddress
{
  private String address;

  private ServiceAddress(String address)
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

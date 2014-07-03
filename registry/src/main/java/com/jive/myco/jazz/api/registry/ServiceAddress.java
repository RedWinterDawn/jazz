package com.jive.myco.jazz.api.registry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;

/**
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

  public static ServiceAddress valueOf(String value)
  {
    return new ServiceAddress(value);
 }
}

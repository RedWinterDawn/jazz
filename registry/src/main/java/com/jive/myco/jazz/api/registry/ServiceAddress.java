package com.jive.myco.jazz.api.registry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;

/**
 * @author John Norton
 */

@Getter
public class ServiceAddress
{
  private String protocol;
  private String server;
  private String port;
  private String path;

  private ServiceAddress(String address)
  {
    Pattern pattern = Pattern.compile("(.*):\\/{2}(.*)\\/(.*)");
    Matcher matcher = pattern.matcher(address);
    if (matcher.matches())
    {
      protocol = matcher.group(1);
      if (matcher.group(2).split(":").length == 1)
      {
        server = matcher.group(2);
      }
      else
      {
        server = matcher.group(2).split(":")[0];
        port = matcher.group(2).split(":")[1];
      }
      path = matcher.group(3);
    }
  }

  @Override
  public String toString()
  {
    if (port == null)
    {
      return String.format("%s://%s/%s", protocol, server, path);
    }
    return String.format("%s://%s:%s/%s", protocol, server, port, path);
  }

  public static ServiceAddress valueOf(String value)
  {
    return new ServiceAddress(value);

  }
}

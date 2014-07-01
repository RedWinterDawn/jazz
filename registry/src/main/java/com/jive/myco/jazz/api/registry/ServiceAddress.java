package com.jive.myco.jazz.api.registry;


import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jnorton on 6/30/14.
 */
public class ServiceAddress {

  @Getter @Setter
  private String protocol;
  @Getter @Setter
  private String server;
  @Getter @Setter
  private String port;
  @Getter @Setter
  private String path;

  @Override
  public String toString() {
    if(port == null){
      return String.format("%s://%s/%s",protocol,server,path);
    }
    return String.format("%s://%s:%s/%s",protocol,server,port,path);
  }

  public static ServiceAddress parseAddress(String address){
    ServiceAddress serviceAddress = new ServiceAddress();
    Pattern pattern =  Pattern.compile("(.*):\\/{2}(.*)\\/(.*)");
    Matcher matcher =  pattern.matcher(address);
    if(matcher.matches()){
      serviceAddress.setProtocol(matcher.group(1));
      if(matcher.group(2).split(":").length == 1){
        serviceAddress.setServer(matcher.group(2));
      }
      else{
        serviceAddress.setServer(matcher.group(2).split(":")[0]);
        serviceAddress.setPort(matcher.group(2).split(":")[1]);
      }
      serviceAddress.setPath(matcher.group(3));
    }
    return serviceAddress;
  }
}

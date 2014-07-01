package com.jive.myco.jazz.api.registry;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceAddressTest {

  @Test
  public void testParseAddressJDBC() throws Exception {
    String address = "jdbc:postgresql://10.199.4.2:5432/pbxs";
    ServiceAddress serviceAddress = ServiceAddress.valueOf(address);

    assertEquals(serviceAddress.getProtocol(),"jdbc:postgresql");
    assertEquals(serviceAddress.getServer(),"10.199.4.2");
    assertEquals(serviceAddress.getPort(),"5432");
    assertEquals(serviceAddress.getPath(),"pbxs");
    assertEquals(serviceAddress.toString(),address);
  }

  @Test
  public void testParseAddressIPNoPort() throws Exception {
    String address = "http://10.199.4.2/pbxs";
    ServiceAddress serviceAddress = ServiceAddress.valueOf(address);

    assertEquals(serviceAddress.getProtocol(),"http");
    assertEquals(serviceAddress.getServer(),"10.199.4.2");
    assertEquals(serviceAddress.getPort(),null);
    assertEquals(serviceAddress.getPath(),"pbxs");
    assertEquals(serviceAddress.toString(),address);
  }


  @Test
  public void testParseAddressIP() throws Exception {
    String address = "http://10.199.4.2:8080/pbxs";
    ServiceAddress serviceAddress = ServiceAddress.valueOf(address);

    assertEquals(serviceAddress.getProtocol(),"http");
    assertEquals(serviceAddress.getServer(),"10.199.4.2");
    assertEquals(serviceAddress.getPort(),"8080");
    assertEquals(serviceAddress.getPath(),"pbxs");
    assertEquals(serviceAddress.toString(),address);
  }

  @Test
  public void testParseAddressDNSNoPort() throws Exception {
    String address = "http://www.jive.com/pbxs";
    ServiceAddress serviceAddress = ServiceAddress.valueOf(address);

    assertEquals(serviceAddress.getProtocol(),"http");
    assertEquals(serviceAddress.getServer(),"www.jive.com");
    assertEquals(serviceAddress.getPort(),null);
    assertEquals(serviceAddress.getPath(),"pbxs");
    assertEquals(serviceAddress.toString(),address);
  }


}
package com.jive.myco.jazz.api.rest;

import java.net.InetAddress;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.jazz.api.core.network.NetworkId;

/**
 *
 * @author David Valeri
 */
public interface RestServiceManager
{
  public PnkyPromise<RestServiceBinding> addService(
      final RestServiceDescriptor restServiceDescriptor, final String contextPath,
      final NetworkId networkId, final InetAddress inetAddress, final Integer port);
}

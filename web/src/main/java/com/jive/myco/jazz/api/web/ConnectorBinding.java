package com.jive.myco.jazz.api.web;

import java.net.InetAddress;

import lombok.Getter;

import com.jive.myco.jazz.api.core.network.NetworkId;

/**
 * Represents the binding of a Web resource to a particular connector that exposes the resource. At
 * its most primitive level, a connector binding represents the host and port on which some resource
 * may be accessed.
 *
 * @author David Valeri
 *
 * @deprecated use {@link com.jive.myco.jazz.api.core.network.ConnectorBinding} instead.
 */
@Getter
@Deprecated
public final class ConnectorBinding extends com.jive.myco.jazz.api.core.network.ConnectorBinding
{
  public ConnectorBinding(final String connectorName, final NetworkId networkId,
      final InetAddress inetAddress, final int port)
  {
    super(connectorName, networkId, inetAddress, port);
  }
}

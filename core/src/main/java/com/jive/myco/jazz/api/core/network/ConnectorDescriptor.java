package com.jive.myco.jazz.api.core.network;

import java.net.InetAddress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a description of a requested binding for a resource, service, etc. to a particular
 * connector that exposes the resource. At its most primitive level, a connector binding represents
 * the host and port on which some resource may be accessed.
 *
 * Connector resolution follows the process below for operations accepting a combination of network
 * ID, IP address, and port.
 * <ol>
 * <li>If network ID is specified:</li>
 * <ol>
 * <li>If IP address is specified, verify that it is on the specified network and use the specified
 * IP address.</li>
 * <li>If IP address is not specified, choose the first IP on the specified network.</li>
 * </ol>
 * <li>If network ID is not specified:</li>
 * <ol>
 * <li>Verify IP address is specified</li>
 * <li>Verify IP address specified is available on a known network and use the resolved network ID.</li>
 * </ol>
 * <li>If port is specified, use the specified port, otherwise use a random port</li> </ol>
 *
 * After resolving the network ID, IP address, and port to use, the receiver of this descriptor
 * creates a connector for the resolved combination. Existing connections for a combination may be
 * reused depending on the context in which this descriptor is supplied. See documentation for the
 * target context for exact behavior in any given context.
 *
 * @author David Valeri
 */
@Builder
@AllArgsConstructor
@Getter
public final class ConnectorDescriptor
{
  /**
   * The optional network ID to bind the resources on.
   */
  private final NetworkId networkId;

  /**
   * The optional IP to bind the resources on.
   */
  private final InetAddress inetAddress;

  /**
   * The optional port to bind the resources on.
   */
  private final Integer port;
}

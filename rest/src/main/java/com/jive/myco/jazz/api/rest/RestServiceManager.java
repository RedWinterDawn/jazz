package com.jive.myco.jazz.api.rest;

import java.net.InetAddress;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.lifecycle.ListenableLifecycled;
import com.jive.myco.jazz.api.core.network.NetworkId;

/**
 * Represents a manager of REST based services.
 * <p>
 * Connector resolution follows the process below for operations accepting a combination of network
 * ID, IP address, and port.
 * <ul>
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
 * <li>If port is specified, use the specified port, otherwise use a random port</li>
 * </ul>
 * </p>
 * <p>
 * After resolving the network ID, IP address, and port to use, the manager creates a connector for
 * the resolved combination. Existing connections for a combination are reused.
 * </p>
 *
 * @author David Valeri
 */
public interface RestServiceManager extends ListenableLifecycled
{
  /**
   * Binds a {@link RestServiceDescriptor set of REST resources} to be served with the provided
   * settings.
   *
   * @param restServiceDescriptor
   *          the set of REST components to be served
   * @param contextPath
   *          the context path to expose the REST resources at
   * @param networkId
   *          the optional network ID to bind the REST resources on
   * @param inetAddress
   *          the optional IP to bind the REST resources on
   * @param port
   *          the optional port to bind the REST resources on
   *
   * @return a future that completes with the binding information after the REST resources have been
   *         bound in the manager
   */
  public PnkyPromise<RestServiceBinding> addService(
      final RestServiceDescriptor restServiceDescriptor, final String contextPath,
      final NetworkId networkId, final InetAddress inetAddress, final Integer port);
}

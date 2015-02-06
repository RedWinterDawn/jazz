package com.jive.myco.jazz.api.web;

import java.net.InetAddress;

import javax.servlet.Servlet;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.lifecycle.ListenableLifecycled;
import com.jive.myco.jazz.api.core.network.ConnectorDescriptor;
import com.jive.myco.jazz.api.core.network.NetworkId;

/**
 * Represents a manager for web resources that may be bound on different connectors.
 * <p>
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
 * <li>If port is specified, use the specified port, otherwise use a random port</li>
 * </ol>
 *
 * After resolving the network ID, IP address, and port to use, the manager creates a connector for
 * the resolved combination. Existing connections for a combination are reused.
 *
 * @author David Valeri
 */
public interface HttpServerManager extends ListenableLifecycled
{
  /**
   * Binds a servlet into the manager and exposes it on the desired connector. Connector resolution
   * is performed as described in the {@link HttpServerManager} documentation.
   *
   * @param servlet
   *          the servlet to bind
   * @param contextPath
   *          the context path under which the servlet is accessible
   * @param networkId
   *          the optional network ID to bind the servlet on
   * @param inetAddress
   *          the optional IP to bind the servlet on
   * @param port
   *          the optional port to bind the servlet on
   *
   * @return a future that completes with the binding information after the servlet has been bound
   *         in the manager
   *
   * @deprecated user
   *             {@link HttpServerManager#addWebApp(WebAppDescriptor, String, NetworkId, InetAddress, Integer)
   *             ) instead
   */
  @Deprecated
  public PnkyPromise<ServletBinding> addServlet(
      final Servlet servlet, final String contextPath,
      final NetworkId networkId, final InetAddress inetAddress, final Integer port);

  /**
   * Binds a collection of Web resources into the manager and exposes them on the desired connector.
   * Connector resolution is performed as described in the {@link HttpServerManager} documentation.
   * <p>
   * A {@link WebAppDescriptor} did not originally contain information regarding context path and
   * connectors. This legacy API method gives precedent to the values supplied as arguments over
   * values supplied in the descriptor itself.
   *
   * @param webAppDescriptor
   *          the descriptor for the resources to bind
   * @param contextPath
   *          the context path under which the resources are accessible
   * @param networkId
   *          the optional network ID to bind the resources on
   * @param inetAddress
   *          the optional IP to bind the resources on
   * @param port
   *          the optional port to bind the resources on
   *
   * @return a future that completes with the binding information after the resources have been
   *         bound in the manager
   *
   * @deprecated use {@link #addWebApp(WebAppDescriptor)} instead.
   */
  @Deprecated
  public PnkyPromise<WebAppBinding> addWebApp(
      final WebAppDescriptor webAppDescriptor, final String contextPath,
      final NetworkId networkId, final InetAddress inetAddress, final Integer port);

  /**
   * Binds a collection of Web resources into the manager and exposes them on the desired
   * connector(s). Connector resolution is performed as described in {@link ConnectorDescriptor}
   * documentation.
   *
   * @param webAppDescriptor
   *          the descriptor for the resources to bind
   *
   * @return a future that completes with the binding information after the resources have been
   *         bound in the manager
   */
  public PnkyPromise<WebAppBinding> addWebApp(final WebAppDescriptor webAppDescriptor);
}

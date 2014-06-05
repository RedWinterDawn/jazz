package com.jive.myco.jazz.api.web;

import java.util.Set;

import javax.servlet.Servlet;

import com.jive.myco.commons.concurrent.PnkyPromise;

/**
 * Represents the binding of a servlet into the {@link HttpServerManager}.
 *
 * @author David Valeri
 */
public interface ServletBinding
{
  /**
   * Returns the context path on which this binding is bound.
   *
   * @return the context path on which this binding is bound
   */
  String getContextPath();

  /**
   * Returns the servlet which is bound by this binding.
   *
   * @return the servlet which is bound by this binding
   */
  Servlet getServlet();

  /**
   * Returns the connector bindings on which this binding is bound.
   *
   * @return the connector bindings on which this binding is bound
   */
  Set<ConnectorBinding> getConnectorBindings();

  /**
   * Removes this binding from the {@link HttpServerManager}.
   *
   * @return a future that completes when this binding has been removed from the
   *         {@link HttpServerManager}
   */
  PnkyPromise<Void> remove();
}

package com.jive.myco.jazz.api.web;

import java.util.Set;

import com.jive.myco.commons.concurrent.PnkyPromise;

/**
 * Represents the binding of a collection of web resources into the {@link HttpServerManager}.
 *
 * @author David Valeri
 */
@SuppressWarnings("deprecation")
public interface WebAppBinding
{
  /**
   * Returns the context path on which this binding is bound.
   *
   * @return the context path on which this binding is bound
   */
  String getContextPath();

  /**
   * Returns the descriptor for the web application which is bound by this binding.
   *
   * @return the descriptor for the web application which is bound by this binding
   */
  WebAppDescriptor getWebAppDescriptor();

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

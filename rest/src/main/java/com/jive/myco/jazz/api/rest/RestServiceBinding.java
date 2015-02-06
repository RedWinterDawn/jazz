package com.jive.myco.jazz.api.rest;

import java.util.Set;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.jazz.api.core.Binding;
import com.jive.myco.jazz.api.web.ConnectorBinding;

/**
 * Represents the binding of a collection of REST resources into the {@link RestServiceManager}.
 *
 * @author David Valeri
 */
@SuppressWarnings("deprecation")
public interface RestServiceBinding extends Binding<Void>
{
  /**
   * Returns the context path on which this binding is bound.
   *
   * @return the context path on which this binding is bound
   */
  String getContextPath();

  /**
   * Returns the descriptor for the REST service which is bound by this binding.
   *
   * @return the descriptor for the REST service which is bound by this binding
   */
  RestServiceDescriptor getRestServiceDescriptor();

  /**
   * Returns the connector bindings on which this binding is bound.
   *
   * @return the connector bindings on which this binding is bound
   */
  Set<ConnectorBinding> getConnectorBindings();

  /**
   * Removes this binding from the {@link RestServiceManager}.
   *
   * @return a future that completes when this binding has been removed from the
   *         {@link RestServiceManager}
   */
  @Override
  PnkyPromise<Void> remove();
}

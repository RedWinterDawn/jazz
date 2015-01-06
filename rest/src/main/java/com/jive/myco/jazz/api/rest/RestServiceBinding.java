package com.jive.myco.jazz.api.rest;

import java.util.Set;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.jazz.api.web.ConnectorBinding;

/**
 *
 * @author David Valeri
 */
@SuppressWarnings("deprecation")
public interface RestServiceBinding
{
  String getContextPath();

  RestServiceDescriptor getRestServiceDescriptor();

  Set<ConnectorBinding> getConnectorBindings();

  PnkyPromise<Void> remove();
}

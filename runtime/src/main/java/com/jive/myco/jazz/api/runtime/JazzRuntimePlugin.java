package com.jive.myco.jazz.api.runtime;

import com.jive.myco.commons.concurrent.PnkyPromise;

/**
 *
 * @author David Valeri
 */
public interface JazzRuntimePlugin
{
  PnkyPromise<Void> init(final JazzRuntimeEnvironment jazzRuntimeEnvironment);

  PnkyPromise<Void> destroy();
}

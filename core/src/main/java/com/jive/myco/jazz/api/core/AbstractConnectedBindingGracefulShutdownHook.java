package com.jive.myco.jazz.api.core;

import com.jive.myco.commons.concurrent.Pnky;
import com.jive.myco.commons.concurrent.PnkyPromise;

/**
 * An implementation that returns completed promises from all hooks. Implementers of
 * {@link ConnectedBindingGracefulShutdownHook} are encouraged to subclass this abstract class to
 * avoid unnecessary boilerplate.
 *
 * @author David Valeri
 */
public abstract class AbstractConnectedBindingGracefulShutdownHook implements
    ConnectedBindingGracefulShutdownHook
{
  @Override
  public PnkyPromise<Void> removeRequested()
  {
    return Pnky.immediatelyComplete();
  }
}

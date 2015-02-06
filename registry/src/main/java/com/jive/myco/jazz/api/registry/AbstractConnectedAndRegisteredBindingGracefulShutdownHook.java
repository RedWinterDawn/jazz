package com.jive.myco.jazz.api.registry;

import com.jive.myco.commons.concurrent.Pnky;
import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.jazz.api.core.AbstractConnectedBindingGracefulShutdownHook;

/**
 * An implementation that returns completed promises from all hooks. Implementers of
 * {@link ConnectedAndRegisteredBindingGracefulShutdownHook} are encouraged to subclass this
 * abstract class to avoid unnecessary boilerplate.
 *
 * @author David Valeri
 */
public abstract class AbstractConnectedAndRegisteredBindingGracefulShutdownHook extends
    AbstractConnectedBindingGracefulShutdownHook implements
    ConnectedAndRegisteredBindingGracefulShutdownHook
{
  @Override
  public PnkyPromise<Void> removeRegistrationRequested()
  {
    return Pnky.immediatelyComplete();
  }
}

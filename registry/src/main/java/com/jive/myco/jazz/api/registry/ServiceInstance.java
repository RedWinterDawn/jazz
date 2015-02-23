package com.jive.myco.jazz.api.registry;

import java.util.Map;
import java.util.concurrent.Executor;

import com.jive.myco.commons.listenable.Listenable;
import com.jive.myco.commons.versions.Version;

/**
 * Represents a service that is registered in the service registry.
 *
 * @see com.jive.myco.jazz.api.registry.ServiceInterface
 * @see com.jive.myco.jazz.api.registry.ServiceInstanceId
 * @see com.jive.myco.jazz.api.registry.ServiceAddress
 * @see com.jive.myco.commons.versions.Version
 *
 * @author David Valeri
 */
public interface ServiceInstance extends Listenable<ServiceInstanceListener>
{
  String getId();

  ServiceInterfaceName getServiceInterfaceName();

  ServiceAddress getServiceAddress();

  Version getServiceInterfaceVersion();

  long getDistance();

  Map<String, String> getProperties();

  /**
   * {@inheritDoc}
   * <p>
   * The listener will be notified via a call to
   * {@link ServiceInstanceListener#registered(ServiceInstance)} upon addition of the provided
   * listener if this instance has not yet been unregistered or a call to
   * {@link ServiceInstanceListener#unregistered(ServiceInstance)} if this instance has already been
   * unregistered.
   */
  @Override
  default void addListener(final ServiceInstanceListener listener)
  {
    Listenable.super.addListener(listener);
  }

  /**
   * {@inheritDoc}
   * <p>
   * The listener will be notified via a call to
   * {@link ServiceInstanceListener#registered(ServiceInstance)} upon addition of the provided
   * listener if this instance has not yet been unregistered or a call to
   * {@link ServiceInstanceListener#unregistered(ServiceInstance)} if this instance has already been
   * unregistered. This courtesy call makes catching up to the current state of the instance easier
   * for listeners as the implementer does not have to handle initialization of the listener state.
   */
  @Override
  void addListener(final ServiceInstanceListener listener, final Executor executor);
}

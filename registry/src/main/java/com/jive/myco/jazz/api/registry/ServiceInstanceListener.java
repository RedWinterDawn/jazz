package com.jive.myco.jazz.api.registry;

/**
 * @author David Valeri
 */
public interface ServiceInstanceListener
{
  void registered(final ServiceInstance serviceInstance);

  void updated(final ServiceInstance serviceInstance);

  void unregistered(final ServiceInstance serviceInstance);
}

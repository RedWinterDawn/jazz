package com.jive.myco.jazz.api.registry;

import java.util.concurrent.Executor;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.lifecycle.ListenableLifecycled;
import com.jive.myco.commons.versions.VersionRange;

/**
 * Used to register and lookup services in the service Registry.
 *
 * @see com.jive.myco.commons.concurrent.PnkyPromise
 * @see com.jive.myco.jazz.api.registry.RegisteredServiceInstanceBinding
 * @see com.jive.myco.jazz.api.registry.ServiceInstanceDescriptor
 *
 * @author John Norton
 */
public interface RegistryManager extends ListenableLifecycled
{
  /**
   * Registers a service instance.
   *
   * @param serviceInstanceDescriptor
   *          information about the service to be registered
   *
   * @return a promise completed with information about the registered service on success or
   *         completed exceptionally with an {@link IllegalStateException} if the manager is not
   *         initialized
   */
  PnkyPromise<RegisteredServiceInstanceBinding> registerService(
      final ServiceInstanceDescriptor serviceInstanceDescriptor);

  /**
   * Creates a supplier, limited to the scope of service instances matching the provided interface
   * name, optional protocol, and optional version range.
   * <p>
   * NOTE: Implementations of this method are not required to provide guaranteed reporting of
   * initialization state as such a guarantee would require internal synchronization. The intent of
   * this synchronous variant is simply to provide a simple interaction for non-asynchronous code
   * that does not wish to or cannot block while waiting for a promise to complete.
   *
   * @param serviceInterfaceName
   *          the interface name to limit the supplier's result to
   * @param protocol
   *          the optional protocol with which to limit the supplier's result to
   * @param versionRange
   *          the optional version range with which to limit the supplier's results to
   *
   * @return a supplier providing results that match the given criteria
   *
   * @throws IllegalStateException
   *           if the manager is not initialized
   */
  ServiceInstanceSupplier supplierSync(final ServiceInterfaceName serviceInterfaceName,
      final String protocol,
      final VersionRange versionRange);

  /**
   * Creates a supplier, limited to the scope of service instances matching the provided interface
   * name, optional protocol, and optional version range.
   *
   * @param serviceInterfaceName
   *          the interface name to limit the supplier's result to
   * @param protocol
   *          the optional protocol with which to limit the supplier's result to
   * @param versionRange
   *          the optional version range with which to limit the supplier's results to
   *
   * @return a promise containing the supplier on success or completed exceptionally with an
   *         {@link IllegalStateException} if the manager is not initialized
   */
  PnkyPromise<ServiceInstanceSupplier> supplier(
      final ServiceInterfaceName serviceInterfaceName,
      final String protocol,
      final VersionRange versionRange);

  /**
   * Subscribe a listener to events related to service instances that match the provided interface
   * name, optional protocol, and optional version range.
   * <p>
   * The listener will be immediately notified of all matching service instances via calls to
   * {@link ServiceInstanceListener#registered(ServiceInstance)} followed by subsequent calls for
   * updates and removals while the subscription is active.
   * <p>
   * NOTE: Implementations of this method are not required to provide guaranteed reporting of
   * initialization state as such a guarantee would require internal synchronization. The intent of
   * this synchronous variant is simply to provide a simple interaction for non-asynchronous code
   * that does not wish to or cannot block while waiting for a promise to complete.
   *
   * @param serviceInterfaceName
   *          the interface name to limit the subscription to
   * @param protocol
   *          the optional protocol with which to limit the subscription
   * @param versionRange
   *          the optional version range with which to limit the subscription
   * @param listener
   *          the listener to notify regarding matching service instances
   *
   * @return the binding that represents the subscription on success
   *
   * @throws IllegalStateException
   *           if the manager is not initialized
   */
  ServiceInstanceSubscriptionBinding subscribeSync(
      final ServiceInterfaceName serviceInterfaceName,
      final String protocol,
      final VersionRange versionRange,
      final ServiceInstanceListener listener);

  /**
   * Subscribe a listener to events related to service instances that match the provided interface
   * name, optional protocol, and optional version range.
   * <p>
   * The listener will be immediately notified of all matching service instances via calls to
   * {@link ServiceInstanceListener#registered(ServiceInstance)} followed by subsequent for updates
   * and removals while the subscription is active.
   *
   * @param serviceInterfaceName
   *          the interface name to limit the subscription to
   * @param protocol
   *          the optional protocol with which to limit the subscription
   * @param versionRange
   *          the optional version range with which to limit the subscription
   * @param listener
   *          the listener to notify regarding matching service instances
   * @param executor
   *          the executor on which the listener is invoked
   *
   * @return the binding that represents the subscription on success
   *
   * @throws IllegalStateException
   *           if the manager is not initialized
   */
  ServiceInstanceSubscriptionBinding subscribeSync(
      final ServiceInterfaceName serviceInterfaceName,
      final String protocol,
      final VersionRange versionRange,
      final ServiceInstanceListener listener,
      final Executor executor);

  /**
   * Subscribe a listener to events related to service instances that match the provided interface
   * name, optional protocol, and optional version range.
   * <p>
   * The listener will be immediately notified of all matching service instances via calls to
   * {@link ServiceInstanceListener#registered(ServiceInstance)} followed by subsequent calls for
   * updates and removals while the subscription is active.
   *
   * @param serviceInterfaceName
   *          the interface name to limit the subscription to
   * @param protocol
   *          the optional protocol with which to limit the subscription
   * @param versionRange
   *          the optional version range with which to limit the subscription
   * @param listener
   *          the listener to notify regarding matching service instances
   *
   * @return a promise completed with the binding that represents the subscription on success or
   *         completed exceptionally with an {@link IllegalStateException} if the manager is not
   *         initialized
   */
  PnkyPromise<ServiceInstanceSubscriptionBinding> subscribe(
      final ServiceInterfaceName serviceInterfaceName,
      final String protocol,
      final VersionRange versionRange,
      final ServiceInstanceListener listener);

  /**
   * Subscribe a listener to events related to service instances that match the provided interface
   * name, optional protocol, and optional version range.
   * <p>
   * The listener will be immediately notified of all matching service instances via calls to
   * {@link ServiceInstanceListener#registered(ServiceInstance)} followed by subsequent for updates
   * and removals while the subscription is active.
   *
   * @param serviceInterfaceName
   *          the interface name to limit the subscription to
   * @param protocol
   *          the optional protocol with which to limit the subscription
   * @param versionRange
   *          the optional version range with which to limit the subscription
   * @param listener
   *          the listener to notify regarding matching service instances
   * @param executor
   *          the executor on which the listener is invoked
   *
   * @return a promise completed with the binding the represents the subscription on success or
   *         completed exceptionally with an {@link IllegalStateException} if the manager is not
   *         initialized
   */
  PnkyPromise<ServiceInstanceSubscriptionBinding> subscribe(
      final ServiceInterfaceName serviceInterfaceName,
      final String protocol,
      final VersionRange versionRange,
      final ServiceInstanceListener listener,
      final Executor executor);
}

package com.jive.myco.jazz.api.runtime;

import com.jive.myco.jazz.api.config.JazzConfiguration;
import com.jive.myco.jazz.api.health.HealthCheck;
import com.jive.myco.jazz.api.registry.ServiceInstanceDescriptor;
import com.jive.myco.jazz.api.rest.RestServiceDescriptor;
import com.jive.myco.jazz.api.web.WebAppDescriptor;

/**
 * A collector of descriptors of boostrap actions that the runtime should perform.
 *
 * @author David Valeri
 *
 * @param <T>
 *          the configuration type used by the runtime
 */
public interface JazzRuntimeLauncherBootstrap<T extends JazzConfiguration>
{
  JazzRuntimeLauncherBootstrap<T> addBootstrapPlugIn(
      final JazzRuntimeLauncherBoostrapPlugin<T> plugin);

  JazzRuntimeLauncherBootstrap<T> addRegisteredServiceInstanceDescriptor(
      final ServiceInstanceDescriptor registeredServiceInstanceDescriptor);

  JazzRuntimeLauncherBootstrap<T> addWebAppDescriptor(
      final WebAppDescriptor webAppDescriptor);

  JazzRuntimeLauncherBootstrap<T> addRestServiceDescriptor(
      final RestServiceDescriptor restServiceDescriptor);

  JazzRuntimeLauncherBootstrap<T> addHealthCheck(
      final HealthCheck healthCheck);
}

package com.jive.myco.jazz.api.runtime;

import java.util.Optional;

import com.codahale.metrics.MetricRegistry;
import com.jive.jotter.client.advanced.api.AdvancedJotterClientFactory;
import com.jive.myco.jazz.api.config.ConfigurationManager;
import com.jive.myco.jazz.api.config.JazzConfiguration;
import com.jive.myco.jazz.api.health.HealthCheckManager;
import com.jive.myco.jazz.api.metrics.MetricsManager;
import com.jive.myco.jazz.api.registry.RegistryManager;
import com.jive.myco.jazz.api.rest.RestServiceManager;
import com.jive.myco.jazz.api.web.HttpServerManager;
import com.jive.myco.lattice.api.ServiceRegistry;

/**
 *
 * @author David Valeri
 */
public interface JazzCore<T extends JazzConfiguration>
{
  Optional<RegistryManager> getRegistryManager();

  Optional<ServiceRegistry> getServiceRegistry();

  Optional<HttpServerManager> getHttpServerManager();

  Optional<RestServiceManager> getRestServiceManager();

  Optional<HealthCheckManager> getHealthCheckManager();

  MetricsManager getMetricsManager();

  MetricRegistry getMetricRegistry();

  ConfigurationManager<T> getConfigurationManager();

  Optional<AdvancedJotterClientFactory> getAdvancedJotterClientFactory();
}

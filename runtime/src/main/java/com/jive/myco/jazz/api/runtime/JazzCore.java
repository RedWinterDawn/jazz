package com.jive.myco.jazz.api.runtime;

import java.util.Optional;

import com.codahale.metrics.MetricRegistry;
import com.jive.myco.commons.hawtdispatch.DispatchQueueBuilder;
import com.jive.myco.isp.client.api.AsyncIspClient;
import com.jive.myco.jazz.api.config.ConfigurationManager;
import com.jive.myco.jazz.api.config.JazzConfiguration;
import com.jive.myco.jazz.api.health.HealthCheckManager;
import com.jive.myco.jazz.api.metrics.MetricsManager;
import com.jive.myco.jazz.api.registry.RegistryManager;
import com.jive.myco.jazz.api.rest.RestServiceManager;
import com.jive.myco.jazz.api.rest.client.RestClientFactoryManager;
import com.jive.myco.jazz.api.rules.czar.model.RulesEngine;
import com.jive.myco.jazz.api.web.HttpServerManager;
import com.jive.v5.jumpy.Jumpy;

/**
 * Provides access to the core components of the Jazz Framework.
 *
 * @param <T>
 *          the type of the configuration object managed by the {@link ConfigurationManager}.
 *
 * @author David Valeri
 */
@SuppressWarnings("deprecation")
public interface JazzCore<T extends JazzConfiguration>
{
  Optional<HttpServerManager> getHttpServerManager();

  Optional<RestServiceManager> getRestServiceManager();

  Optional<HealthCheckManager> getHealthCheckManager();

  MetricsManager getMetricsManager();

  MetricRegistry getMetricRegistry();

  ConfigurationManager<T> getConfigurationManager();

  Optional<com.jive.v5.isp.client.IspClient> getIspClient();

  Optional<AsyncIspClient> getAsyncIspClient();

  /**
   * @deprecated prefer {@link #getRegistryManager()} over this legacy service discovery API.
   */
  @Deprecated
  Optional<Jumpy> getJumpy();

  Optional<RegistryManager> getRegistryManager();

  RulesEngine getRulesEngine();

  DispatchQueueBuilder getDispatchQueueBuilder();

  Optional<RestClientFactoryManager> getRestClientFactoryManager();
}

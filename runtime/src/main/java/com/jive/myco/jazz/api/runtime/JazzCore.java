package com.jive.myco.jazz.api.runtime;

import java.util.Optional;

import com.codahale.metrics.MetricRegistry;
import com.jive.myco.jazz.api.config.ConfigurationManager;
import com.jive.myco.jazz.api.config.JazzConfiguration;
import com.jive.myco.jazz.api.health.HealthCheckManager;
import com.jive.myco.jazz.api.metrics.MetricsManager;
import com.jive.myco.jazz.api.registry.RegistryManager;
import com.jive.myco.jazz.api.rest.RestServiceManager;
import com.jive.myco.jazz.api.rules.czar.model.RulesEngine;
import com.jive.myco.jazz.api.web.HttpServerManager;
import com.jive.v5.isp.client.IspClient;
import com.jive.v5.isp.jumpy.Jumpy;
import com.jive.v5.nurse.api.Nurse;

/**
 * Provides access to the core components of the Jazz Framework.
 *
 * @param <T>
 *          the type of the configuration object managed by the {@link ConfigurationManager}.
 *
 * @author David Valeri
 */
public interface JazzCore<T extends JazzConfiguration>
{
  Optional<HttpServerManager> getHttpServerManager();

  Optional<RestServiceManager> getRestServiceManager();

  Optional<HealthCheckManager> getHealthCheckManager();

  MetricsManager getMetricsManager();

  MetricRegistry getMetricRegistry();

  ConfigurationManager<T> getConfigurationManager();

  Optional<IspClient> getIspClient();

  Optional<Jumpy> getJumpy();

  Optional<Nurse> getNurse();

  Optional<RegistryManager> getRegistryManager();

  RulesEngine getRulesEngine();
}

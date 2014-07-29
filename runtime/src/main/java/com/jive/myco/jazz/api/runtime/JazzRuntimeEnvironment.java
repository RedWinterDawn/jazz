package com.jive.myco.jazz.api.runtime;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.google.common.net.HostAndPort;
import com.jive.myco.jazz.api.core.BuildMetadata;
import com.jive.myco.jazz.api.core.EnvironmentId;
import com.jive.myco.jazz.api.core.coordinates.Coordinates;
import com.jive.myco.jazz.api.core.network.Networks;

/**
 *
 * @author David Valeri
 */
public interface JazzRuntimeEnvironment
{
  void accept(final JazzRuntimeEnvironmentVisitor visitor);

  /**
   * Returns the service name for this environment.
   */
  String getServiceName();

  /**
   * Returns the coordinates for this environment.
   */
  Coordinates getCoordinates();

  // TODO
  Networks getNetworks();

  /**
   * Returns the host and port for the Carbon relay, if available.
   */
  Optional<HostAndPort> getCarbonAddress();

  /**
   * Returns the host and port for the Logstash relay, if available.
   */
  Optional<HostAndPort> getLogstashAddress();

  /**
   * Returns host and port tuples for the ZK ensemble, if available.
   */
  Optional<List<HostAndPort>> getZkAddresses();

  /**
   * Returns resolved properties for the environment.
   */
  Properties getProperties();

  /**
   * Returns the identifier for this environment.
   * <p>
   * An environment represents a broad classification of the space in which the runtime exists. For
   * instance, {@link EnvironmentId} defines constants for the production and staging environments
   * while a runtime executing on a developers workstation will have an entirely different
   * environment.
   */
  EnvironmentId getEnvironmentId();

  /**
   * Returns the resolved build metadata for the launcher starting the runtime.
   */
  Optional<BuildMetadata> getBuildMetadata();
}

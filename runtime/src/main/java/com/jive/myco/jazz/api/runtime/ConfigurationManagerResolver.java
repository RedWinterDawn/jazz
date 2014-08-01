package com.jive.myco.jazz.api.runtime;

import com.jive.myco.jazz.api.config.ConfigurationManager;
import com.jive.myco.jazz.api.config.JazzConfiguration;

/**
 * Strategy interface for resolving a {@link ConfigurationManager} given a
 * {@link JazzRuntimeEnvironment}.
 *
 * @author David Valeri
 *
 * @param <T>
 *          the type of configuration object managed by the resolved configuration manager
 */
public interface ConfigurationManagerResolver<T extends JazzConfiguration>
{
  ConfigurationManager<T> resolve(
      final JazzRuntimeEnvironment jazzRuntimeEnvironment);
}

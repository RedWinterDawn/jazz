package com.jive.myco.jazz.api.runtime;

import com.jive.myco.jazz.api.config.ConfigurationManager;
import com.jive.myco.jazz.api.config.JazzConfiguration;

/**
 *
 * @author David Valeri
 *
 * @param <T>
 */
public interface ConfigurationManagerResolver<T extends JazzConfiguration>
{
  ConfigurationManager<T> resolve(
      final JazzRuntimeEnvironment jazzRuntimeEnvironment);
}

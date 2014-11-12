package com.jive.myco.jazz.api.runtime;

import com.jive.myco.jazz.api.config.JazzConfiguration;

/**
 * Describes the contract for a plug-in to the Jazz Runtime.
 *
 * @author David Valeri
 *
 * @param <T>
 *          the configuration type used by the runtime
 */
public interface JazzRuntimeLauncherBoostrapPlugin<T extends JazzConfiguration>
{
  void init(final JazzRuntimeEnvironment jazzRuntimeEnvironment,
      final JazzCore<T> jazzCore, final JazzRuntimeLauncherBootstrap<T> bootstrap);

  void start(final JazzRuntimeEnvironment jazzRuntimeEnvironment,
      final JazzCore<T> jazzCore);

  void stop(final JazzRuntimeEnvironment jazzRuntimeEnvironment,
      final JazzCore<T> jazzCore);

  void destroy(final JazzRuntimeEnvironment jazzRuntimeEnvironment,
      final JazzCore<T> jazzCore);
}

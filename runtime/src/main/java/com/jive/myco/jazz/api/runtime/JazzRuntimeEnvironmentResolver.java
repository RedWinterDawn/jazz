package com.jive.myco.jazz.api.runtime;

/**
 * Strategy interface for resolving a {@link JazzRuntimeEnvironment} given only basic information
 * about the launcher boostrapping environment.
 *
 * @author David Valeri
 */
public interface JazzRuntimeEnvironmentResolver
{
  /**
   * Returns the resolved environment. The returned environment is fully initialized and ready for
   * use.
   *
   * @param jazzRuntimeLauncher
   *          the launcher requesting environment resolution
   *
   * @return the resolved and fully initialized environment
   */
  JazzRuntimeEnvironment resolve(final JazzRuntimeLauncher jazzRuntimeLauncher);
}

package com.jive.myco.jazz.api.runtime;

/**
 *
 * @author David Valeri
 */
public interface JazzRuntimeEnvironmentResolver
{
  JazzRuntimeEnvironment resolve(final JazzRuntimeLauncher jazzRuntimeLauncher);
}

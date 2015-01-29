package com.jive.myco.jazz.api.runtime;

import com.jive.myco.commons.lifecycle.ListenableLifecycled;
import com.jive.myco.jazz.api.config.JazzConfiguration;

/**
 * @author David Valeri
 *
 * @param <T>
 *          the configuration type used by the runtime
 */
public interface LifecycledJazzRuntimeLauncherBinder<T extends JazzConfiguration>
{
  JazzCore<T> getJazzCore();

  JazzRuntimeEnvironment getJazzRuntimeEnvironment();

  void bind(final ListenableLifecycled binding);

  void bind(final int lifecycleStep, final ListenableLifecycled binding);

  void bind(final LifecycledJazzRuntimeLauncherPlugin<T> plugin);
}

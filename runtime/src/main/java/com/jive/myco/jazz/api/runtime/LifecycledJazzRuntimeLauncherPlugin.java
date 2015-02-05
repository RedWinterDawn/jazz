package com.jive.myco.jazz.api.runtime;

import com.jive.myco.jazz.api.config.JazzConfiguration;

/**
 * Defines the contract for plug-in's that apply some configuration to a
 * {@link LifecycledJazzRuntimeLauncherBinder}.
 *
 * @author David Valeri
 *
 * @param <T>
 *          the configuration type used by the runtime
 */
@FunctionalInterface
public interface LifecycledJazzRuntimeLauncherPlugin<T extends JazzConfiguration>
{
  /**
   * Apply the plug-in's configuration to the supplied binder.
   *
   * @param binder
   *          the binder to apply the plug-in's configuration to
   */
  void configure(final LifecycledJazzRuntimeLauncherBinder<T> binder);
}

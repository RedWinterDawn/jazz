package com.jive.myco.jazz.api.runtime;

import com.jive.myco.jazz.api.config.JazzConfiguration;

/**
 * @author David Valeri
 *
 * @param <T>
 *          the configuration type used by the runtime
 */
@FunctionalInterface
public interface LifecycledJazzRuntimeLauncherPlugin<T extends JazzConfiguration>
{
  void configure(final LifecycledJazzRuntimeLauncherBinder<T> binder);
}

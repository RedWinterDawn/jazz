package com.jive.myco.jazz.api.runtime;

import com.jive.myco.jazz.api.config.JazzConfiguration;

public interface LifecycledJazzRuntimeLauncher<T extends JazzConfiguration> extends
    JazzRuntimeLauncher
{
  void configure(final LifecycledJazzRuntimeLauncherBinder<T> binder);
}

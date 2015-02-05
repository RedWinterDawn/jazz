package com.jive.myco.jazz.api.runtime;

import com.jive.myco.commons.lifecycle.ListenableLifecycled;
import com.jive.myco.jazz.api.config.JazzConfiguration;

/**
 * Defines the contract for the binder suppled applications that leverage a launcher implementing
 * {@link LifecycledJazzRuntimeLauncher}.
 *
 * @author David Valeri
 *
 * @param <T>
 *          the configuration type used by the runtime
 */
public interface LifecycledJazzRuntimeLauncherBinder<T extends JazzConfiguration>
{
  JazzCore<T> getJazzCore();

  JazzRuntimeEnvironment getJazzRuntimeEnvironment();

  /**
   * Bind the lifecycled binding to the {@code 0} lifecycle step.
   *
   * @param binding
   *          the lifecycled item to bind
   */
  void bind(final ListenableLifecycled binding);

  /**
   * Bind the lifecycled binding to the {@code lifecycleStep} lifecycle step.
   *
   * @param lifecycleStep
   *          the lifecycle step to bind {@code binding} to
   * @param binding
   *          the lifecycled item to bind
   */
  void bind(final int lifecycleStep, final ListenableLifecycled binding);

  /**
   * Bind the plug-in.
   *
   * @param plugin
   *          the plugin to bind
   */
  void bind(final LifecycledJazzRuntimeLauncherPlugin<T> plugin);
}

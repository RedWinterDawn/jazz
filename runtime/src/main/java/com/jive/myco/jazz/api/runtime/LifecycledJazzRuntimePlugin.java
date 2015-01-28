package com.jive.myco.jazz.api.runtime;

import com.jive.myco.jazz.api.config.JazzConfiguration;

/**
 * Describes the contract for a plug-in to the Jazz Runtime Launcher. The plug-in phases are invoked
 * in order {@link #init(JazzRuntimeEnvironment, JazzCore)},
 * {@link #start(JazzRuntimeEnvironment, JazzCore)}, {@link #stop(JazzRuntimeEnvironment, JazzCore)}
 * , {@link #destroy(JazzRuntimeEnvironment, JazzCore)}. If there is a failure in
 * {@link #init(JazzRuntimeEnvironment, JazzCore) init} or
 * {@link #start(JazzRuntimeEnvironment, JazzCore) start}, the corresponding shutdown action(s) will
 * be invoked. That is, any lifecycle action that is attempted will have its complementary action
 * performed.
 *
 * @author David Valeri
 *
 * @param <T>
 *          the configuration type used by the runtime
 */
public interface LifecycledJazzRuntimePlugin<T extends JazzConfiguration>
{
  /**
   * Initialize resources during this phase. The initialized resources should not have side effects.
   *
   * @param jazzRuntimeEnvironment
   *          the runtime environment
   * @param jazzCore
   *          the jazz core
   *
   * @throws JazzRuntimeLauncherException
   *           if there is an error during initialization.
   */
  void init(final JazzRuntimeEnvironment jazzRuntimeEnvironment, final JazzCore<T> jazzCore)
      throws JazzRuntimeLauncherException;

  /**
   * Start resources / services during this phase. This is the appropriate phase for performing
   * actions that have side effects such as opening database connections, starting periodic
   * processes, or filling caches.
   *
   * @param jazzRuntimeEnvironment
   *          the runtime environment
   * @param jazzCore
   *          the jazz core
   *
   * @throws JazzRuntimeLauncherException
   *           if there is an error during initialization.
   */
  void start(final JazzRuntimeEnvironment jazzRuntimeEnvironment, final JazzCore<T> jazzCore)
      throws JazzRuntimeLauncherException;

  /**
   * Stop resources / services during this phase. This is the appropriate phase for undoing actions
   * that have side effects such as closing database connections, stopping periodic processes,
   * flushing caches, or stopping thread pools.
   *
   * @param jazzRuntimeEnvironment
   *          the runtime environment
   * @param jazzCore
   *          the jazz core
   *
   * @throws JazzRuntimeLauncherException
   *           if there is an error during initialization.
   */
  void stop(final JazzRuntimeEnvironment jazzRuntimeEnvironment, final JazzCore<T> jazzCore)
      throws JazzRuntimeLauncherException;

  /**
   * Destroy resources during this phase.
   *
   * @param jazzRuntimeEnvironment
   *          the runtime environment
   * @param jazzCore
   *          the jazz core
   *
   * @throws JazzRuntimeLauncherException
   *           if there is an error during initialization.
   */
  void destroy(final JazzRuntimeEnvironment jazzRuntimeEnvironment, final JazzCore<T> jazzCore)
      throws JazzRuntimeLauncherException;
}

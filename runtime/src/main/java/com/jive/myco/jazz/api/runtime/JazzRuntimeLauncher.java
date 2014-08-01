package com.jive.myco.jazz.api.runtime;

/**
 * Describes the interface for the entry point from which Jazz boostraps.
 *
 * @author David Valeri
 */
public interface JazzRuntimeLauncher
{
  /**
   * Returns the value used as the service name in the event that the {@link JazzRuntimeEnvironment}
   * does not provide a means to resolve the service name.
   */
  String getDefaultServiceName();

  /**
   * Returns the arguments provided to the launcher.
   */
  String[] getArgs();

  /**
   * Triggers the runtime to commence an orderly shutdown.
   */
  void shutdown();

  /**
   * Triggers the runtime to commence an orderly shutdown, logging the supplied failure message.
   *
   * @param message
   *          the failure message to log
   */
  void fail(final String message);

  /**
   * Triggers the runtime to commence an orderly shutdown, logging the supplied failure cause.
   *
   * @param e
   *          the exception causing the failure
   */
  void fail(final Exception e);

  /**
   * Triggers the runtime to commence an orderly shutdown, logging the supplied failure message and
   * exception as the cause.
   *
   * @param message
   *          the failure message to log
   * @param e
   *          the exception causing the failure
   */
  void fail(final String message, final Exception e);
}

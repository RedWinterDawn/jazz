package com.jive.myco.jazz.api.config;

/**
 * The interface for observers of {@link JazzConfiguration} instances.
 *
 * @author David Valeri
 *
 * @param <T>
 *          the specific sub-type of the observed {@link JazzConfiguration}
 */
public interface JazzConfigurationListener<T extends JazzConfiguration>
{
  /**
   * Event handler invoked on initial addition ({@link JazzConfiguration#addListener(Object)} (or
   * {@link JazzConfiguration#addListener(Object, java.util.concurrent.Executor)}) of this listener
   * with a {@link ConfigurationManager}.
   *
   * @param configuration
   *          the current configuration at the time this listener was added
   */
  void initialConfiguration(final T configuration);

  /**
   * Event handler invoked on subsequent updates of the observed configuration after the invocation
   * of {@link #initialConfiguration(JazzConfiguration)}.
   *
   * @param configuration
   *          the current configuration
   */
  void onConfigurationChange(final T configuration);
}

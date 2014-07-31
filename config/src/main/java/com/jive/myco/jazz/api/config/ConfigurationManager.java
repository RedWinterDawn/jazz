package com.jive.myco.jazz.api.config;

import java.util.concurrent.Executor;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.listenable.Listenable;

/**
 * This interface describes the entry point for interacting with application configuration within
 * Jazz.
 *
 * @author David Valeri
 *
 * @param <T>
 *          the type of the configuration object managed by the manager
 */
public interface ConfigurationManager<T extends JazzConfiguration> extends
    Listenable<JazzConfigurationListener<? super T>>
{
  /**
   * Returns the current snapshot of the configuration.
   */
  PnkyPromise<T> getConfiguration();

  @Override
  void addListener(final JazzConfigurationListener<? super T> listener);

  @Override
  void addListener(final JazzConfigurationListener<? super T> listener, final Executor executor);

  @Override
  void removeListener(final Object listener);
}

package com.jive.myco.jazz.api.config;

import java.util.concurrent.Executor;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.listenable.Listenable;

/**
 *
 * @author David Valeri
 *
 * @param <T>
 */
public interface ConfigurationManager<T extends JazzConfiguration> extends
    Listenable<JazzConfigurationListener<T>>
{
  PnkyPromise<T> getConfiguration();

  @Override
  void addListener(final JazzConfigurationListener<T> listener);

  @Override
  void addListener(final JazzConfigurationListener<T> listener, final Executor executor);

  @Override
  void removeListener(final Object listener);
}

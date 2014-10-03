package com.jive.myco.jazz.api.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.NonNull;

/**
 * A base class for concrete implementations of {@link JazzConfiguration}. Subclasses should
 * maintain the immutable pattern.
 *
 * @author David Valeri
 */
public abstract class AbstractJazzConfiguration implements JazzConfiguration
{
  @Getter
  private final Map<String, String> logLevels;

  /**
   * Creates a new instance.
   *
   * @param logLevels
   *          see {@link JazzConfiguration#getLogLevels()}
   * @param jazzHealthServicePort
   *          see {@link JazzConfiguration#getJazzHealthServicePort()}
   */
  public AbstractJazzConfiguration(@NonNull final Map<String, String> logLevels)
  {
    this.logLevels = Collections.unmodifiableMap(new HashMap<>(logLevels));
  }
}

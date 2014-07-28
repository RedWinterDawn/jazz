package com.jive.myco.jazz.api.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.Getter;
import lombok.NonNull;

/**
 *
 * @author David Valeri
 */
public abstract class AbstractJazzConfiguration implements JazzConfiguration
{
  @Getter
  private final Map<String, String> logLevels;

  @Getter
  private final Optional<Integer> jazzHealthServicePort;

  public AbstractJazzConfiguration(@NonNull final Map<String, String> logLevels,
      final Integer jazzHealthServicePort)
  {
    this.logLevels = Collections.unmodifiableMap(new HashMap<>(logLevels));
    this.jazzHealthServicePort = Optional.ofNullable(jazzHealthServicePort);
  }
}

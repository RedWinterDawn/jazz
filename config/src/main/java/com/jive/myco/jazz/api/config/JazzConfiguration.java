package com.jive.myco.jazz.api.config;

import java.util.Map;
import java.util.Optional;

/**
 *
 * @author David Valeri
 */
public interface JazzConfiguration
{
  Map<String, String> getLogLevels();

  Optional<Integer> getJazzHealthServicePort();
}

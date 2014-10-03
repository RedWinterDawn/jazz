package com.jive.myco.jazz.api.config;

import java.util.Map;

/**
 * The core configuration for Jazz. Applications, services, etc. may subclass this interface to
 * provide application specific configuration data. Subclasses should maintain the immutable
 * pattern.
 *
 * @author David Valeri
 */
public interface JazzConfiguration
{
  /**
   * Returns the mapping of logger names to log levels such as DEBUG, INFO, WARN, and ERROR used to
   * configure the logging system.
   */
  Map<String, String> getLogLevels();
}

package com.jive.myco.jazz.api.runtime;


/**
 * Represents a runtime environment available when executing via a Main class with
 * {@code public static void main(String[] args)} such as on a developer machine or from a fat JAR.
 *
 * @author David Valeri
 */
public interface StandaloneJazzRuntimeEnvironment extends JazzRuntimeEnvironment
{
  // Marker
}

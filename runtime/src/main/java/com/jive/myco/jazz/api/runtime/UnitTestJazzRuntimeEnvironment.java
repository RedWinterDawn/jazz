package com.jive.myco.jazz.api.runtime;

import java.util.Set;

/**
 * Represents a runtime environment available when executing within a unit or integration test.
 *
 * @author David Valeri
 */
public interface UnitTestJazzRuntimeEnvironment extends JazzRuntimeEnvironment
{
  Set<String> getAlteredSystemPropertyKeys();
}

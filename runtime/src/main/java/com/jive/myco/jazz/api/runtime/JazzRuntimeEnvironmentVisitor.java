package com.jive.myco.jazz.api.runtime;

/**
 * Visitor interface for {@link JazzRuntimeEnvironment}s.
 *
 * @author David Valeri
 */
public interface JazzRuntimeEnvironmentVisitor
{
  void visit(final JinstJazzRuntimeEnvironment environment);

  void visit(final StandaloneJazzRuntimeEnvironment environment);

  void visit(final UnitTestJazzRuntimeEnvironment environment);
}

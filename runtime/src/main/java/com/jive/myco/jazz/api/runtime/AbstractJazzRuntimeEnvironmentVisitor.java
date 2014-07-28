package com.jive.myco.jazz.api.runtime;

/**
 *
 * @author David Valeri
 */
public class AbstractJazzRuntimeEnvironmentVisitor implements
    JazzRuntimeEnvironmentVisitor
{
  protected void visitOther(final JazzRuntimeEnvironment environment)
  {
    // No-Op
  }

  @Override
  public void visit(final JinstJazzRuntimeEnvironment environment)
  {
    visitOther(environment);
  }

  @Override
  public void visit(final StandaloneJazzRuntimeEnvironment environment)
  {
    visitOther(environment);
  }

  @Override
  public void visit(final UnitTestJazzRuntimeEnvironment environment)
  {
    visitOther(environment);
  }
}

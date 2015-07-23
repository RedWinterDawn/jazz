package com.jive.myco.jazz.api.runtime;

/**
 * Abstract visitor implementation for the various types of {@link JazzRuntimeEnvironment}s. All
 * visit operations default to {@link #visitOther(JazzRuntimeEnvironment)}.
 *
 * @author David Valeri
 */
public class AbstractJazzRuntimeEnvironmentVisitor implements
    JazzRuntimeEnvironmentVisitor
{
  /**
   * Fall through handler for unhandled environment types.
   *
   * @param environment
   *          the environment
   */
  protected void visitOther(final JazzRuntimeEnvironment environment)
  {
    // No-Op
  }

  /**
   * Visits {@link JinstJazzRuntimeEnvironment} instances. Defaults to calling
   * {@link #visitOther(JazzRuntimeEnvironment)}.
   *
   * @param environment
   *          the environment
   */
  @Override
  public void visit(final JinstJazzRuntimeEnvironment environment)
  {
    visitOther(environment);
  }

  /**
   * Visits {@link StandaloneJazzRuntimeEnvironment} instances. Defaults to calling
   * {@link #visitOther(JazzRuntimeEnvironment)}.
   *
   * @param environment
   *          the environment
   */
  @Override
  public void visit(final StandaloneJazzRuntimeEnvironment environment)
  {
    visitOther(environment);
  }

  /**
   * Visits {@link UnitTestJazzRuntimeEnvironment} instances. Defaults to calling
   * {@link #visitOther(JazzRuntimeEnvironment)}.
   *
   * @param environment
   *          the environment
   */
  @Override
  public void visit(final UnitTestJazzRuntimeEnvironment environment)
  {
    visitOther(environment);
  }

  /**
   * Visits {@link DockerJazzRuntimeEnvironment} instances. Defaults to calling
   * {@link #visitOther(JazzRuntimeEnvironment)}.
   *
   * @param environment
   *          the environment
   */
  @Override
  public void visit(final DockerJazzRuntimeEnvironment environment)
  {
    visitOther(environment);
  }
}

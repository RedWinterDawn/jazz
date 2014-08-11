package com.jive.myco.jazz.api.context;

import java.util.concurrent.Executor;

/**
 * Allows running a task on an executor with the provided context.
 * <p>
 * All default methods from {@link Executor} will be executed with the current context data from the
 * invoking thread.
 *
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
public interface ContextExecutor extends Executor
{
  /**
   * Executes the given task, adding the given context to the current context data.
   *
   * @see #execute(Runnable)
   */
  void execute(Runnable task, JazzContext context);
}

package com.jive.myco.jazz.api.context;

import java.util.Map;
import java.util.concurrent.Executor;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Allows running a task on an executor with the provided context.
 * <p>
 * All default methods from {@link Executor} will be executed with the current context data from the
 * invoking thread.
 *
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
@RequiredArgsConstructor
public class ContextExecutor implements Executor
{
  @NonNull
  private final Executor delegate;

  /**
   * Executes the given task, adding the given context to the current context data.
   *
   * @see Executor#execute(Runnable)
   */
  public void execute(final Runnable task, final Map<String, String> context)
  {
    getDelegate().execute(new ContextWrappedRunnable(task, context));
  }

  @Override
  public void execute(final Runnable command)
  {
    getDelegate().execute(new ContextWrappedRunnable(command));
  }

  protected Executor getDelegate()
  {
    return delegate;
  }
}

package com.jive.myco.jazz.api.context;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Wrapped {@link ExecutorService} that will provide context for each executed task.
 * <p>
 * All default methods from {@link ExecutorService} will be executed with the current context data
 * from the invoking thread.
 *
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
public interface ContextExecutorService extends ExecutorService, ContextExecutor
{
  /**
   * Executes the given task, adding the given context to the current context data.
   *
   * @see #submit(Callable)
   */
  <T> Future<T> submit(final Callable<T> task, final Map<String, String> context);

  /**
   * Executes the given task, adding the given context to the current context data.
   *
   * @see #submit(Runnable, Object)
   */
  <T> Future<T> submit(final Runnable task, final T result, final Map<String, String> context);

  /**
   * Executes the given task, adding the given context to the current context data.
   *
   * @see #submit(Runnable)
   */
  Future<?> submit(final Runnable task, final Map<String, String> context);
}

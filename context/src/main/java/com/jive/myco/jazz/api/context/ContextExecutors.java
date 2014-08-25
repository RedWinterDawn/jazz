package com.jive.myco.jazz.api.context;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

import org.fusesource.hawtdispatch.DispatchQueue;

/**
 * Utility class for wrapping executors to provide context data for tasks executed on the executor.
 *
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
public final class ContextExecutors
{
  public static ContextExecutor wrap(final Executor executor)
  {
    return new ContextExecutor(executor);
  }

  public static ContextExecutorService wrap(final ExecutorService executorService)
  {
    return new ContextExecutorService(executorService);
  }

  public static ContextScheduledExecutorService wrap(
      final ScheduledExecutorService executorService)
  {
    return new ContextScheduledExecutorService(executorService);
  }

  public static ContextDispatchQueue wrap(final DispatchQueue queue)
  {
    return new ContextDispatchQueue(queue);
  }
}

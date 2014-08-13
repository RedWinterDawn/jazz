package com.jive.myco.jazz.api.context;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.fusesource.hawtdispatch.DispatchQueue;
import org.fusesource.hawtdispatch.Task;

/**
 * Wraps a {@link DispatchQueue} to provide context to the tasks executed on the queue.
 *
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
public interface ContextDispatchQueue extends DispatchQueue, ContextExecutor
{
  /**
   * Execute the given task on this queue with the provided context added to the current context.
   *
   * @see #execute(Task)
   */
  void execute(final Task task, final Map<String, String> context);

  /**
   * Execute the given task on this queue with the provided context added to the current context.
   *
   * @see #executeAfter(long, TimeUnit, Runnable)
   */
  void executeAfter(final long delay, final TimeUnit unit, final Map<String, String> context,
      final Runnable runnable);

  /**
   * Execute the given task on this queue with the provided context added to the current context.
   *
   * @see #executeAfter(long, TimeUnit, Task)
   */
  void executeAfter(final long delay, final TimeUnit unit, final Map<String, String> context,
      final Task task);
}

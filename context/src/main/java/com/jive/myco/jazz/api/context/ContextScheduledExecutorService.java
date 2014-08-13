package com.jive.myco.jazz.api.context;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Wrapped {@link ScheduledExecutorService} that will provide context for each task.
 * <p>
 * All default methods from {@link ScheduledExecutorService} will be executed with the current
 * context data from the invoking thread.
 *
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
public interface ContextScheduledExecutorService
    extends ScheduledExecutorService, ContextExecutorService
{
  /**
   * Executes the given task, adding the given context to the current context data.
   *
   * @see #schedule(Runnable, long, TimeUnit, JazzContext)
   */
  ScheduledFuture<?> schedule(final Runnable command, final long delay, final TimeUnit unit,
      final Map<String, String> context);

  /**
   * Executes the given task, adding the given context to the current context data.
   *
   * @see #schedule(Callable, long, TimeUnit)
   */
  <V> ScheduledFuture<V> schedule(final Callable<V> callable, final long delay, final TimeUnit unit,
      final Map<String, String> context);

  /**
   * Executes the given task, adding the given context to the current context data.
   *
   * @see #scheduleAtFixedRate(Runnable, long, long, TimeUnit)
   */
  ScheduledFuture<?> scheduleAtFixedRate(final Runnable command, final long initialDelay,
      final long period, final TimeUnit unit, final Map<String, String> context);

  /**
   * Executes the given task, adding the given context to the current context data.
   *
   * @see #scheduleWithFixedDelay(Runnable, long, long, TimeUnit)
   */
  ScheduledFuture<?> scheduleWithFixedDelay(final Runnable command, final long initialDelay,
      final long delay, final TimeUnit unit, final Map<String, String> context);
}

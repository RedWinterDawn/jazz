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
public class ContextScheduledExecutorService extends
    ContextExecutorService implements ScheduledExecutorService
{

  public ContextScheduledExecutorService(final ScheduledExecutorService delegate)
  {
    super(delegate);
  }

  /**
   * Executes the given task, adding the given context to the current context data.
   *
   * @see #schedule(Runnable, long, TimeUnit)
   */
  public ScheduledFuture<?> schedule(final Runnable command, final long delay, final TimeUnit unit,
      final Map<String, String> context)
  {
    return getDelegate().schedule(new ContextWrappedRunnable(command, context), delay, unit);
  }

  /**
   * Executes the given task, adding the given context to the current context data.
   *
   * @see #schedule(Callable, long, TimeUnit)
   */
  public <V> ScheduledFuture<V> schedule(final Callable<V> callable, final long delay,
      final TimeUnit unit,
      final Map<String, String> context)
  {
    return getDelegate().schedule(new ContextWrappedCallable<>(callable, context), delay, unit);
  }

  /**
   * Executes the given task, adding the given context to the current context data.
   *
   * @see #scheduleAtFixedRate(Runnable, long, long, TimeUnit)
   */
  public ScheduledFuture<?> scheduleAtFixedRate(final Runnable command, final long initialDelay,
      final long period,
      final TimeUnit unit, final Map<String, String> context)
  {
    return getDelegate().scheduleAtFixedRate(
        new ContextWrappedRunnable(command, context), initialDelay, period, unit);
  }

  /**
   * Executes the given task, adding the given context to the current context data.
   *
   * @see #scheduleWithFixedDelay(Runnable, long, long, TimeUnit)
   */
  public ScheduledFuture<?> scheduleWithFixedDelay(final Runnable command, final long initialDelay,
      final long delay,
      final TimeUnit unit, final Map<String, String> context)
  {
    return getDelegate().scheduleWithFixedDelay(
        new ContextWrappedRunnable(command, context), initialDelay, delay, unit);
  }

  @Override
  public ScheduledFuture<?> schedule(final Runnable command, final long delay, final TimeUnit unit)
  {
    return getDelegate().schedule(new ContextWrappedRunnable(command), delay, unit);
  }

  @Override
  public <V> ScheduledFuture<V> schedule(final Callable<V> callable, final long delay,
      final TimeUnit unit)
  {
    return getDelegate().schedule(new ContextWrappedCallable<>(callable), delay, unit);
  }

  @Override
  public ScheduledFuture<?> scheduleAtFixedRate(final Runnable command, final long initialDelay,
      final long period,
      final TimeUnit unit)
  {
    return getDelegate().scheduleAtFixedRate(
        new ContextWrappedRunnable(command), initialDelay, period, unit);
  }

  @Override
  public ScheduledFuture<?> scheduleWithFixedDelay(final Runnable command, final long initialDelay,
      final long delay,
      final TimeUnit unit)
  {
    return getDelegate().scheduleWithFixedDelay(
        new ContextWrappedRunnable(command), initialDelay, delay, unit);
  }

  @Override
  protected ScheduledExecutorService getDelegate()
  {
    return (ScheduledExecutorService) super.getDelegate();
  }
}

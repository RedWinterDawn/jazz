package com.jive.myco.jazz.api.context;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * Wrapped {@link ExecutorService} that will provide context for each executed task.
 * <p>
 * All default methods from {@link ExecutorService} will be executed with the current context data
 * from the invoking thread.
 *
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
public class ContextExecutorService extends ContextExecutor implements
    ExecutorService
{

  public ContextExecutorService(final ExecutorService delegate)
  {
    super(delegate);
  }

  /**
   * Executes the given task, adding the given context to the current context data.
   *
   * @see #submit(Callable)
   */
  public <T> Future<T> submit(final Callable<T> task, final Map<String, String> context)
  {
    return getDelegate().submit(new ContextWrappedCallable<>(task, context));
  }

  /**
   * Executes the given task, adding the given context to the current context data.
   *
   * @see #submit(Callable)
   */
  public <T> Future<T> submit(final Runnable task, final T result, final Map<String, String> context)
  {
    return getDelegate().submit(new ContextWrappedRunnable(task, context), result);
  }

  /**
   * Executes the given task, adding the given context to the current context data.
   *
   * @see #submit(Callable)
   */
  public Future<?> submit(final Runnable task, final Map<String, String> context)
  {
    return getDelegate().submit(new ContextWrappedRunnable(task, context));
  }

  @Override
  public void shutdown()
  {
    getDelegate().shutdown();
  }

  @Override
  public List<Runnable> shutdownNow()
  {
    return getDelegate().shutdownNow();
  }

  @Override
  public boolean isShutdown()
  {
    return getDelegate().isShutdown();
  }

  @Override
  public boolean isTerminated()
  {
    return getDelegate().isTerminated();
  }

  @Override
  public boolean awaitTermination(final long timeout, final TimeUnit unit)
      throws InterruptedException
  {
    return getDelegate().awaitTermination(timeout, unit);
  }

  @Override
  public <T> Future<T> submit(final Callable<T> task)
  {
    return getDelegate().submit(new ContextWrappedCallable<>(task));
  }

  @Override
  public <T> Future<T> submit(final Runnable task, final T result)
  {
    return getDelegate().submit(new ContextWrappedRunnable(task), result);
  }

  @Override
  public Future<?> submit(final Runnable task)
  {
    return getDelegate().submit(new ContextWrappedRunnable(task));
  }

  @Override
  public <T> List<Future<T>> invokeAll(final Collection<? extends Callable<T>> tasks)
      throws InterruptedException
  {
    return getDelegate().invokeAll(
        tasks.stream()
            .map((task) -> new ContextWrappedCallable<>(task))
            .collect(Collectors.toList()));
  }

  @Override
  public <T> List<Future<T>> invokeAll(final Collection<? extends Callable<T>> tasks,
      final long timeout,
      final TimeUnit unit) throws InterruptedException
  {
    return getDelegate().invokeAll(
        tasks.stream()
            .map((task) -> new ContextWrappedCallable<>(task))
            .collect(Collectors.toList()), timeout, unit);
  }

  @Override
  public <T> T invokeAny(final Collection<? extends Callable<T>> tasks)
      throws InterruptedException, ExecutionException
  {
    return getDelegate().invokeAny(
        tasks.stream()
            .map((task) -> new ContextWrappedCallable<>(task))
            .collect(Collectors.toList()));
  }

  @Override
  public <T> T invokeAny(final Collection<? extends Callable<T>> tasks, final long timeout,
      final TimeUnit unit)
      throws InterruptedException, ExecutionException, TimeoutException
  {
    return getDelegate().invokeAny(
        tasks.stream()
            .map((task) -> new ContextWrappedCallable<>(task))
            .collect(Collectors.toList()), timeout, unit);
  }

  @Override
  protected ExecutorService getDelegate()
  {
    return (ExecutorService) super.getDelegate();
  }
}

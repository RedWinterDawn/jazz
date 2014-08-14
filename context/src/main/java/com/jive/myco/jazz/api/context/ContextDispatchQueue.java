package com.jive.myco.jazz.api.context;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.fusesource.hawtdispatch.DispatchQueue;
import org.fusesource.hawtdispatch.Metrics;
import org.fusesource.hawtdispatch.Task;

/**
 * Wraps a {@link DispatchQueue} to provide context to the tasks executed on the queue.
 *
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
public class ContextDispatchQueue extends ContextExecutor implements DispatchQueue
{

  public ContextDispatchQueue(final DispatchQueue delegate)
  {
    super(delegate);
  }

  /**
   * Execute the given task on this queue with the provided context added to the current context.
   *
   * @see #execute(Task)
   */
  public void execute(final Task task, final Map<String, String> context)
  {
    getDelegate().execute(new ContextWrappedTask(task, context));
  }

  /**
   * Execute the given task on this queue with the provided context added to the current context.
   *
   * @see #executeAfter(long, TimeUnit, Runnable)
   */
  public void executeAfter(final long delay, final TimeUnit unit,
      final Map<String, String> context, final Runnable runnable)
  {
    getDelegate().executeAfter(delay, unit, new ContextWrappedTask(runnable, context));
  }

  /**
   * Execute the given task on this queue with the provided context added to the current context.
   *
   * @see #executeAfter(long, TimeUnit, Task)
   */
  public void executeAfter(final long delay, final TimeUnit unit,
      final Map<String, String> context, final Task task)
  {
    getDelegate().executeAfter(delay, unit, new ContextWrappedTask(task, context));
  }

  @Override
  public QueueType getQueueType()
  {
    return getDelegate().getQueueType();
  }

  @Override
  public DispatchQueue createQueue(final String label)
  {
    return new ContextDispatchQueue(getDelegate().createQueue(label));
  }

  @Override
  public void execute(final Runnable runnable)
  {
    getDelegate().execute(new ContextWrappedTask(runnable));
  }

  @Override
  public void execute(final Task task)
  {
    getDelegate().execute(new ContextWrappedTask(task));
  }

  @Override
  public void executeAfter(final long delay, final TimeUnit unit, final Runnable runnable)
  {
    getDelegate().executeAfter(delay, unit, new ContextWrappedRunnable(runnable));
  }

  @Override
  public void executeAfter(final long delay, final TimeUnit unit, final Task task)
  {
    getDelegate().executeAfter(delay, unit, new ContextWrappedTask(task));
  }

  @Override
  public String getLabel()
  {
    return getDelegate().getLabel();
  }

  @Override
  public void setLabel(final String label)
  {
    getDelegate().setLabel(label);
  }

  @Override
  public boolean isExecuting()
  {
    return getDelegate().isExecuting();
  }

  @Override
  public void assertExecuting()
  {
    getDelegate().assertExecuting();
  }

  @Override
  public void profile(final boolean on)
  {
    getDelegate().profile(on);
  }

  @Override
  public boolean profile()
  {
    return getDelegate().profile();
  }

  @Override
  public Metrics metrics()
  {
    return getDelegate().metrics();
  }

  @Override
  public void setTargetQueue(final DispatchQueue queue)
  {
    getDelegate().setTargetQueue(queue);
  }

  @Override
  public DispatchQueue getTargetQueue()
  {
    return getDelegate().getTargetQueue();
  }

  @Override
  public void suspend()
  {
    getDelegate().suspend();
  }

  @Override
  public void resume()
  {
    getDelegate().resume();
  }

  @Override
  public boolean isSuspended()
  {
    return getDelegate().isSuspended();
  }

  @Override
  protected DispatchQueue getDelegate()
  {
    return (DispatchQueue) super.getDelegate();
  }
}

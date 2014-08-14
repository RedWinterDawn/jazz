package com.jive.myco.jazz.api.context;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
public class ContextWrappedCallable<V> implements Callable<V>
{
  private final Callable<V> delegate;
  private final Map<String, String> context;

  public ContextWrappedCallable(final Callable<V> wrapped)
  {
    this(wrapped, null);
  }

  public ContextWrappedCallable(final Callable<V> wrapped, final Map<String, String> context)
  {
    this.delegate = wrapped;
    this.context = JazzContextManager.toMap();

    if (context != null)
    {
      this.context.putAll(context);
    }
  }

  @Override
  public V call() throws Exception
  {
    try
    {
      JazzContextManager.putAll(context);
      return delegate.call();
    }
    finally
    {
      JazzContextManager.clear();
    }
  }
}

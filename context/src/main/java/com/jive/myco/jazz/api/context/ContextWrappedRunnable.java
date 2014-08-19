package com.jive.myco.jazz.api.context;

import java.util.Map;

/**
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
public class ContextWrappedRunnable implements Runnable
{
  private final Runnable wrapped;
  private final Map<String, String> context;

  public ContextWrappedRunnable(final Runnable wrapped)
  {
    this(wrapped, null);
  }

  public ContextWrappedRunnable(final Runnable wrapped, final Map<String, String> context)
  {
    this.wrapped = wrapped;
    this.context = JazzContextManager.toMap();

    if (context != null)
    {
      this.context.putAll(context);
    }
  }

  @Override
  public void run()
  {
    try
    {
      JazzContextManager.putAll(context);
      wrapped.run();
    }
    finally
    {
      JazzContextManager.clear();
    }
  }
}

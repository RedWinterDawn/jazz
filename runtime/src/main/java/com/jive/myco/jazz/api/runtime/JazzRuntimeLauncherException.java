package com.jive.myco.jazz.api.runtime;

/**
 * Top level generic exception class for issues in the launcher.
 *
 * @author David Valeri
 */
public class JazzRuntimeLauncherException extends RuntimeException
{
  private static final long serialVersionUID = 1L;

  public JazzRuntimeLauncherException()
  {
    super();
  }

  public JazzRuntimeLauncherException(final String message, final Throwable cause)
  {
    super(message, cause);
  }

  public JazzRuntimeLauncherException(final String message)
  {
    super(message);
  }

  public JazzRuntimeLauncherException(final Throwable cause)
  {
    super(cause);
  }
}

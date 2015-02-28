package com.jive.myco.jazz.api.registry.exceptions;

/**
 * Exception received when service registration fails.
 *
 * @author John Norton
 */
public class RegistryManagerException extends RuntimeException
{
  private static final long serialVersionUID = -3387516993124229948L;

  public RegistryManagerException(final Throwable cause)
  {
    super(cause);
  }

  public RegistryManagerException()
  {
    super();
  }

  public RegistryManagerException(final String message, final Throwable cause)
  {
    super(message, cause);
  }

  public RegistryManagerException(final String message)
  {
    super(message);
  }
}

package com.jive.myco.jazz.api.registry.exceptions;

/**
 * Exception received when service registration fails.
 *
 * @author John Norton
 */
public class RegistryManagerException extends Exception
{
  private static final long serialVersionUID = -3387516993124229948L;

  public RegistryManagerException(final Throwable cause)
  {
    super(cause);
  }
}

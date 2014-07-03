package com.jive.myco.jazz.api.registry.exceptions;

/**
 * Exception received when service registration fails;
 * @author John Norton
 */


public class FailedToRegisterServiceException extends Exception {

  static final long serialVersionUID = -3387516993124229948L;

  public FailedToRegisterServiceException(Throwable cause)
  {
    super(cause);
  }

}

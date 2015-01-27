package com.jive.myco.jazz.api.rest.client;

/**
 * A representation of an error in the REST client related to IO operations.
 *
 * @author David Valeri
 */
public class IoRestClientException extends RestClientException
{

  private static final long serialVersionUID = 1L;

  public IoRestClientException()
  {
    super();
  }

  public IoRestClientException(final String message, final Throwable cause)
  {
    super(message, cause);
  }

  public IoRestClientException(final String message)
  {
    super(message);
  }

  public IoRestClientException(final Throwable cause)
  {
    super(cause);
  }
}

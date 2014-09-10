package com.jive.myco.jazz.api.rest.client;

/**
 * Base exception class for errors thrown from the REST client framework.
 *
 * @author David Valeri
 */
public class RestClientException extends RuntimeException
{
  private static final long serialVersionUID = -1007929739748850333L;

  public RestClientException()
  {
    super();
  }

  public RestClientException(final String message, final Throwable cause)
  {
    super(message, cause);
  }

  public RestClientException(final String message)
  {
    super(message);
  }

  public RestClientException(final Throwable cause)
  {
    super(cause);
  }
}

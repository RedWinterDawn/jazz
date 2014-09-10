package com.jive.myco.jazz.api.rest.client;

/**
 * Represents errors related to encountering content with an unsupported media type as in
 * serialization activities.
 *
 * @author David Valeri
 */
public class UnsupportedMediaTypeException extends RestClientException
{
  private static final long serialVersionUID = -5467519373452651508L;

  public UnsupportedMediaTypeException()
  {
    super();
  }

  public UnsupportedMediaTypeException(final String message, final Throwable cause)
  {
    super(message, cause);
  }

  public UnsupportedMediaTypeException(final String message)
  {
    super(message);
  }

  public UnsupportedMediaTypeException(final Throwable cause)
  {
    super(cause);
  }
}

package com.jive.myco.jazz.api.rest.client;


/**
 * A representation of an error in the REST client related to a non-200 series HTTP response code.
 *
 * @author David Valeri
 */
@SuppressWarnings("deprecation")
public class HttpRestClientException extends RestClientHttpException
{
  private static final long serialVersionUID = -1007929739748850333L;

  public HttpRestClientException(final int responseCode, final String reasonPhrase)
  {
    this(responseCode, reasonPhrase, null, null);
  }

  public HttpRestClientException(final int responseCode, final String reasonPhrase,
      final Throwable cause)
  {
    this(responseCode, reasonPhrase, null, cause);
  }

  public HttpRestClientException(final int responseCode, final String reasonPhrase,
      final String message)
  {
    this(responseCode, reasonPhrase, message, null);
  }

  public HttpRestClientException(final int responseCode, final String reasonPhrase,
      final String message,
      final Throwable cause)
  {
    super(responseCode, reasonPhrase, message, cause);
  }
}

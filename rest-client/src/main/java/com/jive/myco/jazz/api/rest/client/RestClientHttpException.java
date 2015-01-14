package com.jive.myco.jazz.api.rest.client;

import lombok.Getter;

/**
 * A representation of an error in the REST client related to a non-200 series HTTP response code.
 *
 * @author David Valeri
 */
public class RestClientHttpException extends RestClientException
{
  private static final long serialVersionUID = -1007929739748850333L;

  @Getter
  private final int responseCode;

  @Getter
  private final String reasonPhrase;

  public RestClientHttpException(final int responseCode, final String reasonPhrase)
  {
    this(responseCode, reasonPhrase, null, null);
  }

  public RestClientHttpException(final int responseCode, final String reasonPhrase,
      final Throwable cause)
  {
    this(responseCode, reasonPhrase, null, cause);
  }

  public RestClientHttpException(final int responseCode, final String reasonPhrase,
      final String message)
  {
    this(responseCode, reasonPhrase, message, null);
  }

  public RestClientHttpException(final int responseCode, final String reasonPhrase,
      final String message,
      final Throwable cause)
  {
    super(message == null ? cause.getMessage() : message, cause);

    this.responseCode = responseCode;
    this.reasonPhrase = reasonPhrase;
  }

  @Override
  public String toString()
  {
    final String s = getClass().getName();
    final String message = getLocalizedMessage();
    return (message != null) ? (s + ": [" + responseCode + "(" + reasonPhrase + ")]" + message) : s;
  }
}

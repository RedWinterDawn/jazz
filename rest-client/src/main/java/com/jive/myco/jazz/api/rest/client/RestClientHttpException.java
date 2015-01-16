package com.jive.myco.jazz.api.rest.client;

import lombok.Getter;

/**
 * A representation of an error in the REST client related to a non-200 series HTTP response code.
 *
 * @author David Valeri
 *
 * @deprecated use {@link HttpRestClientException} instead. This class will be removed from the
 *             hierarchy in a future release.
 */
@Deprecated
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
  public String getMessage()
  {
    String message = "[" + responseCode + " (" + reasonPhrase + ")]";
    final String superMessage = super.getMessage();

    if (superMessage != null)
    {
      message += " " + superMessage;
    }

    return message;
  }
}

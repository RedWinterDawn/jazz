package com.jive.myco.jazz.api.rest.client;

/**
 * Represents the condition in which the REST client has been configured with a dynamically resolved
 * endpoint but endpoint is currently available.
 *
 * @author David Valeri
 */
public class NoServiceEndpointAvailableRestClientException extends RestClientException
{
  private static final long serialVersionUID = 2360046681190512847L;

  public NoServiceEndpointAvailableRestClientException()
  {
    super();
  }

  public NoServiceEndpointAvailableRestClientException(final String message, final Throwable cause)
  {
    super(message, cause);
  }

  public NoServiceEndpointAvailableRestClientException(final String message)
  {
    super(message);
  }

  public NoServiceEndpointAvailableRestClientException(final Throwable cause)
  {
    super(cause);
  }
}

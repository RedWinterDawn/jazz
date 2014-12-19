package com.jive.myco.jazz.api.rest.client.ExceptionHandler;

import javax.ws.rs.core.Response;

import com.google.common.collect.ImmutableList;

/**
 * Created by btran on 12/19/14.
 */
public class RetryResult implements RestExceptionHandlingResult
{
  private final Exception causes;
  private final Response responses;

  public RetryResult(final Exception causes, final Response responses)
  {
    this.causes = causes;
    this.responses = responses;
  }
}

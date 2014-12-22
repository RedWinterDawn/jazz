package com.jive.myco.jazz.api.rest.client.ExceptionHandler;

import java.util.Optional;

import javax.ws.rs.core.Response;

import com.google.common.collect.ImmutableList;

/**
 * @author Binh Tran
 * @author Rich Adams
 */
public class RetryResult implements RestExceptionHandlingResult
{
  private final Optional<Exception> causes;
  private final Optional<Response> responses;
  private final String baseUrl;

  public RetryResult(final Optional<Exception> causes, final Optional<Response> responses,
      final String baseUrl)
  {
    this.causes = causes;
    this.responses = responses;
    this.baseUrl = baseUrl;
  }

  @Override
  public String getBaseUrl()
  {
    return baseUrl;
  }
}

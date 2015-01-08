package com.jive.myco.jazz.api.rest.client.responsehandler;

import java.util.function.Function;

import lombok.Getter;

/**
 * @author Binh Tran
 * @author Rich Adams
 */
@Getter
public abstract class RestResponseHandlingResult
{
  private String baseUrl;
  private int RetryCount;

  protected RestResponseHandlingResult(final String baseUrl, final int retryCount)
  {
    this.baseUrl = baseUrl;
    RetryCount = retryCount;
  }

  private static class BaseRestResponseHandlingResult extends RestResponseHandlingResult
  {

    protected BaseRestResponseHandlingResult(final String baseUrl, final int retryCount)
    {
      super(baseUrl, retryCount);
    }
  }

  public static Function<String, RestResponseHandlingResult> firstResultBuilder =
      (s) -> new BaseRestResponseHandlingResult(s, 0);
}

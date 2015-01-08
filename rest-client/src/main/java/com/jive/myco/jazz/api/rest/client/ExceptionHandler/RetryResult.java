package com.jive.myco.jazz.api.rest.client.exceptionhandler;

import lombok.Getter;

/**
 * @author Binh Tran
 * @author Rich Adams
 */
@Getter
public class RetryResult implements RestExceptionHandlingResult
{
  private final String baseUrl;
  private final int retryCount;

  public RetryResult(final String baseUrl, final int retryCount)
  {
    this.baseUrl = baseUrl;
    this.retryCount = retryCount;
  }
}

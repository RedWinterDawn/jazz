package com.jive.myco.jazz.api.rest.client.ExceptionHandler;

import lombok.Getter;

/**
 * @author Binh Tran
 * @author Rich Adams
 */
@Getter
public class ExceptionalResult implements RestExceptionHandlingResult
{
  private final Exception exception;
  private final String baseUrl;
  private final int retryCount;

  public ExceptionalResult(Exception e, final String baseUrl, final int retryCount)
  {
    this.exception = e;
    this.baseUrl = baseUrl;
    this.retryCount = retryCount;
  }
}

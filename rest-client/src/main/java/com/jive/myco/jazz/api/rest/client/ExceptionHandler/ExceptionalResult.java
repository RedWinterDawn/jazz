package com.jive.myco.jazz.api.rest.client.ExceptionHandler;

import lombok.Getter;

/**
 * @author Binh Tran
 * @author Rich Adams
 */
public class ExceptionalResult implements RestExceptionHandlingResult
{
  @Getter
  private final Exception exception;

  public ExceptionalResult(Exception e)
  {
    this.exception = e;
  }

  @Override
  public String getBaseUrl()
  {
    return "";
  }
}

package com.jive.myco.jazz.api.rest.client.ExceptionHandler;

import lombok.Getter;

/**
 * Created by btran on 12/19/14.
 */
public class ExceptionalResult implements RestExceptionHandlingResult
{
  @Getter
  private final Exception exception;

  public ExceptionalResult(Exception e)
  {
    this.exception = e;
  }
}

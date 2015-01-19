package com.jive.myco.jazz.api.rest.client.responsehandler;

import lombok.Getter;

/**
 * @author Binh Tran
 * @author Rich Adams
 */
@Getter
public class ExceptionalRestResponseHandlerResult implements RestResponseHandlerResult
{
  private final Exception exception;

  public ExceptionalRestResponseHandlerResult(final Exception exception)
  {
    this.exception = exception;
  }
}

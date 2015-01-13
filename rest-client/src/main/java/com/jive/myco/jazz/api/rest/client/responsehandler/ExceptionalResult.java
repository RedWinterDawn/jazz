package com.jive.myco.jazz.api.rest.client.responsehandler;

import lombok.Getter;

/**
 * @author Binh Tran
 * @author Rich Adams
 */
@Getter
public class ExceptionalResult extends AbstractRestResponseHandlerResult
{
  private final Exception exception;

  public ExceptionalResult(Exception e, final String baseUrl, final int retryCount)
  {
    super(baseUrl, retryCount);
    this.exception = e;
  }
}

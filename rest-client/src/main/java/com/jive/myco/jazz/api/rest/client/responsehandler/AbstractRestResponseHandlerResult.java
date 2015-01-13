package com.jive.myco.jazz.api.rest.client.responsehandler;

import java.util.function.Function;

import lombok.Getter;

/**
 * @author Binh Tran
 * @author Rich Adams
 */
@Getter
public abstract class AbstractRestResponseHandlerResult implements RestResponseHandlerResult
{
  private String baseUrl;
  private int retryCount;

  protected AbstractRestResponseHandlerResult(final String baseUrl, final int retryCount)
  {
    this.baseUrl = baseUrl;
    this.retryCount = retryCount;
  }

  public static Function<String, RestResponseHandlerResult> firstResultBuilder =
      (s) -> new BaseAbstractRestResponseHandlerResult(s, 0);
}

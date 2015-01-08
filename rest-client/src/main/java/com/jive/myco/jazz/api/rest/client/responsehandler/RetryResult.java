package com.jive.myco.jazz.api.rest.client.responsehandler;

import lombok.Getter;

/**
 * @author Binh Tran
 * @author Rich Adams
 */
@Getter
public class RetryResult extends RestResponseHandlingResult
{

  public RetryResult(final String baseUrl, final int retryCount)
  {
    super(baseUrl, retryCount);
  }
}

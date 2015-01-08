package com.jive.myco.jazz.api.rest.client.responsehandler;

import lombok.Getter;

/**
 * @author Binh Tran
 * @author Rich Adams
 */
@Getter
public class DeserializingResult extends RestResponseHandlingResult
{

  private DeserializingResult(final String baseUrl, final int retryCount)
  {
    super(baseUrl, retryCount);
  }

  public static final DeserializingResult INSTANCE = new DeserializingResult("", -1);

}

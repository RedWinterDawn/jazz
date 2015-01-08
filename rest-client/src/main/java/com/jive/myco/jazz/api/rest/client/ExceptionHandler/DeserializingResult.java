package com.jive.myco.jazz.api.rest.client.exceptionhandler;

import lombok.Getter;

/**
 * @author Binh Tran
 * @author Rich Adams
 */
@Getter
public class DeserializingResult implements RestExceptionHandlingResult
{

  private final String baseUrl;
  private final int retryCount;

  private DeserializingResult(final String baseUrl, final int retryCount)
  {
    this.baseUrl = baseUrl;
    this.retryCount = retryCount;
  }

  public static final DeserializingResult INSTANCE = new DeserializingResult("", -1);

}

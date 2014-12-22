package com.jive.myco.jazz.api.rest.client.ExceptionHandler;

/**
 * @author Binh Tran
 * @author Rich Adams
 */
public class DeserializingResult implements RestExceptionHandlingResult
{
  private DeserializingResult()
  {
    // no op
  }

  public static final DeserializingResult INSTANCE = new DeserializingResult();

  @Override
  public String getBaseUrl()
  {
    return "";
  }
}

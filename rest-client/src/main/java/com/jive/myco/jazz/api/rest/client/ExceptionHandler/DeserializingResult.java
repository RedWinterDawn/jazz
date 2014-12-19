package com.jive.myco.jazz.api.rest.client.ExceptionHandler;

/**
 * Created by btran on 12/19/14.
 */
public class DeserializingResult implements RestExceptionHandlingResult
{
  private DeserializingResult()
  {
    // no op
  }

  public static final DeserializingResult INSTANCE = new DeserializingResult();
}

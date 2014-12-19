package com.jive.myco.jazz.api.rest.client.ExceptionHandler;

import javax.ws.rs.core.Response;

/**
 * Created by btran on 12/19/14.
 */
public interface RestExceptionHandler
{
  RestExceptionHandlingResult handle(int retryCount, Response respone,
      RestExceptionHandlingResult lastResult);
}

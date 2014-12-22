package com.jive.myco.jazz.api.rest.client.ExceptionHandler;

import java.util.Optional;

import javax.ws.rs.core.Response;

/**
 * Created by btran on 12/19/14.
 */
@FunctionalInterface
public interface RestExceptionHandler
{
  RestExceptionHandlingResult handle(int retryCount, Optional<Response> response,
      Optional<Exception> exception,
      Optional<RestExceptionHandlingResult> lastResult);
}

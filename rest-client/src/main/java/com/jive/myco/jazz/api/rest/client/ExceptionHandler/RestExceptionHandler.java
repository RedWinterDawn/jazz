package com.jive.myco.jazz.api.rest.client.exceptionhandler;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.ws.rs.core.Response;

/**
 * @author Binh Tran
 * @author Rich Adams
 */
public interface RestExceptionHandler
{
  /**
   * Handle a failure while requesting from a remote resource
   * @param response the response received during the latest attempt, if exists
   * @param exception the exception thrown during the latest attempt, if exists
   * @param urlSupplier the function used to get the next url, if applicable
   * @param lastResult the result from the last handled error
   * @return
   */
  RestExceptionHandlingResult handle(
      Optional<Response> response,
      Optional<Exception> exception,
      Function<List<String>, Optional<String>> urlSupplier,
      RestExceptionHandlingResult lastResult
                                    );

  RestExceptionHandler expectedStatuses(int[] statuses);

  RestExceptionHandler maxRetries(int maxRetries);
}

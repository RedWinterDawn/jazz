package com.jive.myco.jazz.api.rest.client.responsehandler;

import java.util.Optional;
import java.util.function.Function;

import javax.ws.rs.core.Response;

import com.jive.v5.jumpy.JumpyRecordFilter;

/**
 * @author Binh Tran
 * @author Rich Adams
 */
public interface RestResponseHandler
{
  /**
   * Handle a failure while requesting from a remote resource
   * @param response the response received during the latest attempt, if exists
   * @param exception the exception thrown during the latest attempt, if exists
   * @param urlSupplier the function used to get the next url, if applicable
   * @param lastResult the result from the last handled error
   * @return
   */
  AbstractRestResponseHandlerResult handle(
      Optional<Response> response,
      Optional<Exception> exception,
      Function<JumpyRecordFilter<String>, Optional<String>> urlSupplier,
      RestResponseHandlerResult lastResult);

  RestResponseHandler expectedStatuses(int[] statuses);

  RestResponseHandler maxRetries(int maxRetries);
}

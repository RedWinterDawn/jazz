package com.jive.myco.jazz.api.rest.client.responsehandler;

import java.util.Optional;

import javax.ws.rs.core.Response;

import com.jive.myco.jazz.api.rest.client.RestRequest;
import com.jive.myco.jazz.api.rest.client.RestRequestState;

/**
 * Describes the contract for response handling within the REST framework. Implementations have
 * control over core response handling decisions without needing to be aware of the implementation
 * details involved in fulfilling such decisions.
 *
 * @author Binh Tran
 * @author Rich Adams
 */
public interface RestResponseHandler
{
  /**
   * Handle the response from a remote resource.
   *
   * @param restRequest
   *          the REST request model for the request that generated the response being handled
   * @param restRequest
   *          the REST request state model for the request that generated the response being handled
   * @param response
   *          the response received during the latest attempt, if exists
   * @param exception
   *          the exception thrown during the latest attempt, if exists
   *
   * @return the result describing the action that the framework should take
   */
  RestResponseHandlerResult handle(
      final RestRequest restRequest,
      final RestRequestState restRequestState,
      final Optional<Response> response,
      final Optional<Exception> exception);
}

package com.jive.myco.jazz.api.rest.client;

import com.jive.myco.jazz.api.rest.client.annotations.RestResponseStrategy;
import com.jive.myco.jazz.api.rest.client.responsehandler.RestResponseStrategyModel;

/**
 * Encapsulates the state information for a single rest request from the user.
 *
 * @author David Valeri
 */
public interface RestRequest
{
  /**
   * Returns the HTTP method the request uses.
   */
  String getHttpMethod();

  /**
   * Returns the configuration for the response handler based on the {@link RestResponseStrategy
   * configuration}.
   */
  RestResponseStrategyModel getRestResponseStrategyModel();

  /**
   * Returns the media types, those produced by the server, that the request will accept in a reply.
   */
  String[] getProduces();

  /**
   * Returns the possible media types, those accepted by the server, into which the request may be
   * encoded.
   */
  String[] getConsumes();
}

package com.jive.myco.jazz.api.rest.client;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import com.jive.myco.jazz.api.registry.ServiceInstanceSupplier;

/**
 * Encapsulates the state of a {@link RestRequest REST resquest}.
 *
 * @author David Valeri
 */
public interface RestRequestState
{
  /**
   * Returns the unique ID for the request.
   */
  String getId();

  /**
   * Returns the immutable list of base URLs to which the request has been sent.
   */
  List<String> getBaseUrls();

  /**
   * Returns the most recently used base URL to which the request has been sent.
   */
  String getLastBaseUrl();

  /**
   * Returns the zero based retry counter. The initial request is "0". The value increments for each
   * subsequent retry attempt.
   */
  int getRetryCount();

  /**
   * Returns the function used source base URLs when a {@link ServiceInstanceSupplier} is not
   * available. This method or {@link #getServiceInstanceSupplier()} will return a non-null result.
   */
  Function<Collection<? extends String>, String> getUrlSupplier();

  /**
   * Returns the service instance supplier used to source base URLs. This method or
   * {@link #getServiceInstanceSupplier()} will return a non-null result.
   */
  ServiceInstanceSupplier getServiceInstanceSupplier();
}

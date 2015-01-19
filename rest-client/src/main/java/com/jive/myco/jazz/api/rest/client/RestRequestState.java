package com.jive.myco.jazz.api.rest.client;

import java.util.List;

import com.jive.v5.jumpy.JumpyRecordFilterCriteria;
import com.jive.v5.jumpy.RestrictedSupplier;

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
   *
   * @return
   */
  String getLastBaseUrl();

  /**
   * Returns the zero based retry counter. The initial request is "0". The value increments for each
   * subsequent retry attempt.
   */
  int getRetryCount();

  /**
   * Returns the restricted supplier used to source base URLs.
   */
  RestrictedSupplier<String, JumpyRecordFilterCriteria> getUrlSupplier();
}

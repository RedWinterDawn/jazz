package com.jive.myco.jazz.api.rest.client;

import java.lang.reflect.Method;
import java.net.URI;

import com.google.common.base.Stopwatch;

/**
 * Listener interface for REST clients produced via the {@link RestClientFactory}.
 *
 * @author Theo
 */
public interface RestClientListener
{
  void onSuccess(final Method method, final URI uri, final String httpMethod, final Stopwatch timer);

  void onTransportFailure(final Method method, final URI uri, final String httpMethod,
      final Throwable t, final Stopwatch timer);
}

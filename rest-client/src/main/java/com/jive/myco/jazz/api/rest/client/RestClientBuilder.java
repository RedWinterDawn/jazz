package com.jive.myco.jazz.api.rest.client;

import java.util.concurrent.Executor;
import java.util.function.Supplier;

import javax.ws.rs.ext.ParamConverterProvider;

import com.jive.myco.jazz.api.web.HttpClientHeaderDecorator;
import com.jive.v5.jumpy.model.RestrictedSupplier;

/**
 * Builder for a REST client.
 *
 * @author David Valeri
 * @author Binh Tran
 *
 * @param <T>
 *          the interface type for the returned client
 */
public interface RestClientBuilder<T>
{
  RestClientBuilder<T> addRestClientSerializer(final RestClientSerializer mapper);

  RestClientBuilder<T> addListener(final RestClientListener listener);

  RestClientBuilder<T> addListener(final RestClientListener listener, final Executor executor);

  RestClientBuilder<T> addProvider(final ParamConverterProvider provider);

  RestClientBuilder<T> url(final String url);

  RestClientBuilder<T> url(final Supplier<String> urlSupplier);

  RestClientBuilder<T> url(final RestrictedSupplier<String> urlRestrictedSupplier);

  RestClientBuilder<T> headerDecorator(final HttpClientHeaderDecorator headerDecorator);

  T build();
}

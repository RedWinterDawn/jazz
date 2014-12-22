package com.jive.myco.jazz.api.rest.client;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

import javax.ws.rs.ext.ParamConverterProvider;

import com.google.common.base.Function;
import com.jive.myco.jazz.api.rest.client.ExceptionHandler.RestExceptionHandlerProvider;
import com.jive.myco.jazz.api.web.HttpClientHeaderDecorator;

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

  RestClientBuilder<T> addProvider(final RestExceptionHandlerProvider restExceptionHandlerProvider);

  RestClientBuilder<T> url(final String url);

  RestClientBuilder<T> url(final Supplier<String> urlSupplier);

  RestClientBuilder<T> url(final Function<List<String>, Optional<String>> filterFunction);

  RestClientBuilder<T> headerDecorator(final HttpClientHeaderDecorator headerDecorator);

  T build();
}

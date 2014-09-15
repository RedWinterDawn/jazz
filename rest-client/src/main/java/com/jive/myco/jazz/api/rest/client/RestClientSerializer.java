package com.jive.myco.jazz.api.rest.client;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * Interface for serialization providers used within a REST client created via
 * {@link RestClientFactory}.
 *
 * @author David Valeri
 */
public interface RestClientSerializer
{
  Set<String> getSupportedMediaTypes();

  Object deserialize(final InputStream is, final Type type, final String mediaType);

  byte[] serializeToBytes(final Object o, final String mediaType);
}

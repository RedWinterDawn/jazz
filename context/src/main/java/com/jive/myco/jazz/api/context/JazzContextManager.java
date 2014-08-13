package com.jive.myco.jazz.api.context;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Manager for the context in an application. Only 1 of these should be available in an application.
 *
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
public interface JazzContextManager
{
  static final String JAZZ_CONTEXT_ID_KEY = "jazz.context.id";

  static final String JAZZ_TRACE_ID_KEY = "jazz.trace.id";

  static final String JAZZ_RUNTIME_APPLICATION_VERSION_KEY =
      "jazz.runtime.application.version";

  static final String JAZZ_RUNTIME_SERVICE_NAME_KEY = "jazz.runtime.service.name";

  static final String JAZZ_RUNTIME_COORDINATES_KEY = "jazz.runtime.coordinates";

  static final String JAZZ_RUNTIME_ENVIRONMENT_ID_KEY = "jazz.runtime.environment.id";

  static final String JAZZ_RUNTIME_ENVIRONMENT_BRANCH_KEY =
      "jazz.runtime.environment.branch";

  /**
   * Get a context parameter from the local context first, then from the incoming context if not
   * available.
   *
   * @param key
   *          the non-{@code null} context key
   * @return the context value
   */
  String get(final String key);

  /**
   * Get a context parameter or {@code defaultValue} if not present.
   *
   * @param key
   *          the the non-{@code null} context key
   * @param defaultValue
   *          the default value if not present
   * @return the context value or default value if not present
   */
  String get(final String key, final String defaultValue);

  /**
   * Get a context parameter, invoking {@code defaultProvider} if not present for the default value.
   *
   * @param key
   *          the non-{@code null} context key
   * @param defaultProvider
   *          provider to invoke if the {@code key} is not present
   * @return the context value or default value if not present
   */
  String get(final String key, final Supplier<String> defaultProvider);

  /**
   * Set a context parameter.
   *
   * @param key
   *          the non-{@code null} context key
   * @param value
   *          the context value
   * @return this instance for chaining
   */
  JazzContextManager put(final String key, final String value);

  /**
   * Set the value for {@code key} if the value is absent.
   *
   * @param key
   *          the the non-{@code null} context key
   * @param valueIfAbsent
   *          the value to set if not already set
   * @return this instance for chaining
   */
  JazzContextManager putIfAbsent(final String key, final String valueIfAbsent);

  /**
   * Set the value for {@code key} if the value is absent invoking the provided
   * {@code valueSupplier} to get the value to set.
   *
   * @param key
   *          the the non-{@code null} context key
   * @param valueSupplier
   *          a supplier to lazily provide the value if absent
   * @return this instance for chaining
   */
  JazzContextManager putIfAbsent(final String key, final Supplier<String> valueSupplier);

  /**
   * Put all the given values into the context.
   *
   * @param values
   *          the values to add to the context
   */
  JazzContextManager putAll(final Map<String, String> values);

  /**
   * Put all the given values into the context but only if they do not already exist in the context.
   *
   * @param values
   *          the values to add to the context
   * @return this instance for chaining
   */
  JazzContextManager putAllIfAbsent(final Map<String, String> values);

  /**
   * Set the value for {@code key} if the value is absent.
   *
   * @param key
   *          the key for which to remove the value
   *
   * @return this instance for chaining
   */
  JazzContextManager remove(final Object key);

  /**
   * Clear all the context parameters.
   *
   * @return this instance for chaining
   */
  JazzContextManager clear();

  /**
   * Transform the current context into a map which <em>excludes</em> values from the local system
   * context. The resulting map will not be backed by this instance. Modifications to the returned
   * map will not affect this context.
   */
  Map<String, String> toMap();

  /**
   * Provides a Stream from all the values in the context.
   */
  Stream<Map.Entry<String, String>> stream();
}

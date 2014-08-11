package com.jive.myco.jazz.api.context;

import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
public interface JazzContext
{
  /**
   * Pattern for valid characters for a context key.
   */
  static final Pattern KEY_PATTERN = Pattern.compile("[\\w.-]+");

  /**
   * Get a context parameter.
   *
   * @param key
   *          the non-{@code null} context key
   * @return the context value
   */
  String get(String key);

  /**
   * Get a context parameter or {@code defaultValue} if not present.
   *
   * @param key
   *          the the non-{@code null} context key
   * @param defaultValue
   *          the default value if not present
   * @return the context value or default value if not present
   */
  String get(String key, String defaultValue);

  /**
   * Get a context parameter, invoking {@code defaultProvider} if not present for the default value.
   *
   * @param key
   *          the non-{@code null} context key
   * @param defaultProvider
   *          provider to invoke if the {@code key} is not present
   * @return the context value or default value if not present
   */
  String get(String key, Supplier<String> defaultProvider);

  /**
   * Get an immutable copy of this context.
   *
   * @return a copy of this context
   */
  JazzContext copy();

  /**
   * Get this context in mutable form. The returned context will no longer be backed by this
   * instance and additions to the returned mutable context will not be visible by this context.
   */
  MutableJazzContext mutableCopy();

  /**
   * Transform this context into a map. The resulting map will not be backed by this instance.
   * Modifications to the returned map will not affect this context.
   */
  Map<String, String> toMap();

  /**
   * Provides a Stream from all the values in the context.
   */
  Stream<Map.Entry<String, String>> stream();
}

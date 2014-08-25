package com.jive.myco.jazz.api.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.apache.commons.lang.RandomStringUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

/**
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JazzContextManager
{
  /*
   * Common keys used in the Jazz Context
   */

  public static final String JAZZ_CONTEXT_ID_KEY = "jazz.context.id";

  public static final String JAZZ_TRACE_ID_KEY = "jazz.trace.id";

  public static final String JAZZ_RUNTIME_APPLICATION_VERSION_KEY =
      "jazz.app.version";

  public static final String JAZZ_RUNTIME_SERVICE_NAME_KEY = "jazz.rt.service.name";

  public static final String JAZZ_RUNTIME_COORDINATES_KEY = "jazz.rt.coordinates";

  public static final String JAZZ_RUNTIME_ENVIRONMENT_ID_KEY = "jazz.rt.env.id";

  public static final String JAZZ_RUNTIME_ENVIRONMENT_BRANCH_KEY =
      "jazz.rt.env.branch";

  /**
   * Generated context ID length
   *
   * @see #JAZZ_CONTEXT_ID_KEY
   */
  private static final int JAZZ_CONTEXT_ID_LENGTH = 32;

  /**
   * Pattern for valid characters for a context key.
   */
  public static final Pattern KEY_PATTERN = Pattern.compile("[\\w.-]+");

  /**
   * Context that is local to the system and global across the application
   */
  private static volatile Map<String, String> localContext = Maps.newConcurrentMap();

  /**
   * Context that is available on a per-thread/context basis
   */
  private static final ThreadLocal<Map<String, String>> context = ThreadLocal
      .withInitial(JazzContextManager::createContext);

  private static final JazzContextManager INSTANCE = new JazzContextManager();

  /**
   * Initialize the local context with the given properties.
   *
   * @param localContext
   *          the local context values to use
   */
  public static void initialize(final Map<String, String> localContext)
  {
    JazzContextManager.localContext = ImmutableMap.copyOf(localContext);
  }

  /**
   * Get a context parameter from the local context first, then from the incoming context if not
   * available.
   *
   * @param key
   *          the non-{@code null} context key
   * @return the context value
   */
  public static String get(final String key)
  {
    return Optional
        .ofNullable(localContext.get(key))
        .orElseGet(() -> context.get().get(key));
  }

  /**
   * Get a context parameter or {@code defaultValue} if not present.
   *
   * @param key
   *          the the non-{@code null} context key
   * @param defaultValue
   *          the default value if not present
   * @return the context value or default value if not present
   */
  public static String get(final String key, final String defaultValue)
  {
    return Optional
        .ofNullable(localContext.get(key))
        .orElse(context.get().getOrDefault(key, defaultValue));
  }

  /**
   * Get a context parameter, invoking {@code defaultProvider} if not present for the default value.
   *
   * @param key
   *          the non-{@code null} context key
   * @param defaultProvider
   *          provider to invoke if the {@code key} is not present
   * @return the context value or default value if not present
   */
  public static String get(final String key, final Supplier<String> defaultProvider)
  {
    return Optional
        .ofNullable(localContext.get(key))
        .orElse(
            Optional
                .ofNullable(context.get().get(key))
                .orElseGet(defaultProvider));
  }

  /**
   * Set a context parameter.
   *
   * @param key
   *          the non-{@code null} context key
   * @param value
   *          the context value
   * @return this instance for chaining
   */
  public static JazzContextManager put(final String key, final String value)
  {
    checkKey(key);

    context.get().put(key, value);
    return INSTANCE;
  }

  /**
   * Set the value for {@code key} if the value is absent.
   *
   * @param key
   *          the the non-{@code null} context key
   * @param valueIfAbsent
   *          the value to set if not already set
   * @return this instance for chaining
   */
  public static JazzContextManager putIfAbsent(final String key, final String valueIfAbsent)
  {
    checkKey(key);

    context.get().putIfAbsent(key, valueIfAbsent);
    return INSTANCE;
  }

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
  public static JazzContextManager putIfAbsent(final String key,
      final Supplier<String> valueSupplier)
  {
    checkKey(key);

    context.get().computeIfAbsent(key, (k) -> valueSupplier.get());
    return INSTANCE;
  }

  /**
   * Put all the given values into the context.
   *
   * @param values
   *          the values to add to the context
   */
  public static JazzContextManager putAll(@NonNull final Map<String, String> values)
  {
    values.keySet().forEach(JazzContextManager::checkKey);

    context.get().putAll(values);
    return INSTANCE;
  }

  /**
   * Put all the given values into the context but only if they do not already exist in the context.
   *
   * @param values
   *          the values to add to the context
   * @return this instance for chaining
   */
  public static JazzContextManager putAllIfAbsent(@NonNull final Map<String, String> values)
  {
    values.keySet().forEach(JazzContextManager::checkKey);

    values.entrySet().forEach(
        (entry) -> context.get().putIfAbsent(entry.getKey(), entry.getValue()));
    return INSTANCE;
  }

  /**
   * Set the value for {@code key} if the value is absent.
   *
   * @param key
   *          the key for which to remove the value
   *
   * @return this instance for chaining
   */
  public static JazzContextManager remove(final Object key)
  {
    context.get().remove(key);
    return INSTANCE;
  }

  /**
   * Clear all the context parameters.
   *
   * @return this instance for chaining
   */
  public static JazzContextManager clear()
  {
    context.remove();
    return INSTANCE;
  }

  /**
   * Transform the current context into a map which <em>excludes</em> values from the local system
   * context. The resulting map will not be backed by this instance. Modifications to the returned
   * map will not affect this context.
   */
  public static Map<String, String> toMap()
  {
    return new HashMap<>(context.get());
  }

  /**
   * Provides a Stream from all the values in the context.
   */
  public static Stream<Map.Entry<String, String>> stream()
  {
    return toMap().entrySet().stream();
  }

  private static HashMap<String, String> createContext()
  {
    final HashMap<String, String> newContext = new HashMap<>();

    newContext.put(JAZZ_CONTEXT_ID_KEY,
        RandomStringUtils.randomAlphanumeric(JAZZ_CONTEXT_ID_LENGTH));

    return newContext;
  }

  static void checkKey(final String key)
  {
    Preconditions.checkArgument(JazzContextManager.KEY_PATTERN.matcher(key).matches(),
        "Invalid context key [%s], only numbers, letters, and . _ - characters allowed", key);
  }
}

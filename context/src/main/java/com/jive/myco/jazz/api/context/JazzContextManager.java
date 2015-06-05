package com.jive.myco.jazz.api.context;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.RandomStringUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

/**
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
@Slf4j
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
   * Number of random bits used to generate a trace ID.
   *
   * @see #JAZZ_TRACE_ID_KEY
   * @see #JAZZ_TRACE_ID_RADIX
   */
  private static final int JAZZ_TRACE_ID_RANDOM_BITS = 130;

  /**
   * Generated trace ID radix. Using {@code 32} ensures the length of the generated ID is constant
   * length based on the number of random bits.
   *
   * @see #JAZZ_TRACE_ID_KEY
   * @see #JAZZ_TRACE_ID_RANDOM_BITS
   */
  private static final int JAZZ_TRACE_ID_RADIX = 32;

  /**
   * Pattern for valid characters for a context key.
   */
  public static final Pattern KEY_PATTERN = Pattern.compile("[\\w.-]+");

  /**
   * Context that is local to the system and global across the application
   */
  private static volatile Map<String, ContextProperty> immutableLocalContext = Maps
      .newConcurrentMap();

  /**
   * Context that is available on a per-thread/context basis
   */
  private static final ThreadLocal<Map<String, ContextProperty>> context = ThreadLocal
      .withInitial(JazzContextManager::createContext);

  private static final JazzContextManager INSTANCE = new JazzContextManager();

  /**
   * Initialize the local context with the given immutable private properties.
   *
   * @param immutablePrivateProperties
   *          the immutable private context values to use
   */
  public static void initialize(final Map<String, String> immutablePrivateProperties)
  {
    JazzContextManager.immutableLocalContext = Collections.unmodifiableMap(
        immutablePrivateProperties.entrySet()
            .stream()
            .collect(
                Collectors.<Map.Entry<String, String>, String, ContextProperty> toMap(
                    Map.Entry::getKey,
                    (entry) -> new ContextProperty(entry.getValue(), Scope.PRIVATE))));
  }

  /**
   * Returns a context property, either public or private.
   *
   * @param key
   *          the non-{@code null} context key
   * @return the context value
   */
  public static String get(final String key)
  {
    return get(key, () -> null, null);
  }

  /**
   * Returns a context property, either public or private, or {@code defaultValue} if not present.
   *
   * @param key
   *          the the non-{@code null} context key
   * @param defaultValue
   *          the default value if {@code key} is not present
   *
   * @return the context value or default value if not present
   */
  public static String get(final String key, final String defaultValue)
  {
    return get(key, () -> defaultValue, null);
  }

  /**
   * Returns a context parameter, either public or private, invoking {@code defaultProvider} if not
   * present for the default value.
   *
   * @param key
   *          the non-{@code null} context key
   * @param defaultProvider
   *          provider to invoke if {@code key} is not present
   *
   * @return the context value or default value if not present
   */
  public static String get(final String key, final Supplier<String> defaultProvider)
  {
    return get(key, defaultProvider, null);
  }

  /**
   * Returns a context parameter, in the requested {@code scope}, invoking {@code defaultProvider}
   * if not present for the default value.
   *
   * @param key
   *          the non-{@code null} context key
   * @param defaultProvider
   *          provider to invoke if {@code key} is not present
   * @param scope
   *          the scope to restrict the lookup to or {@code null} for either scope
   *
   * @return the context value or default value if not present in the requested scope
   */
  public static String get(final String key, final Supplier<String> defaultProvider,
      final Scope scope)
  {
    return Optional
        .ofNullable(
            Optional
                .ofNullable(immutableLocalContext.get(key))
                .orElseGet(() -> context.get().get(key)))
        .filter((cp) -> scope == null || cp.getScope() == scope)
        .map(ContextProperty::getValue)
        .orElseGet(defaultProvider);
  }

  /**
   * Sets a public context property.
   *
   * @param key
   *          the non-{@code null} context key
   * @param value
   *          the context value
   *
   * @return this instance for chaining
   */
  public static JazzContextManager put(final String key, final String value)
  {
    return put(key, value, Scope.PUBLIC);
  }

  /**
   * Sets a context property in the requested scope.
   *
   * @param key
   *          the non-{@code null} context key
   * @param value
   *          the context value
   * @param scope
   *          the scope in which to set the property
   *
   * @return this instance for chaining
   */
  public static JazzContextManager put(final String key, final String value,
      @NonNull final Scope scope)
  {
    checkKey(key);

    context.get().put(key, new ContextProperty(value, scope));

    return INSTANCE;
  }

  /**
   * Sets a public context property for {@code key} if the property is absent.
   *
   * @param key
   *          the the non-{@code null} context key
   * @param valueIfAbsent
   *          the value to set if not already set
   *
   * @return this instance for chaining
   */
  public static JazzContextManager putIfAbsent(final String key, final String valueIfAbsent)
  {
    return putIfAbsent(key, valueIfAbsent, Scope.PUBLIC);
  }

  /**
   * Sets a context property in the requested scope for {@code key} if the property is absent.
   *
   * @param key
   *          the the non-{@code null} context key
   * @param valueIfAbsent
   *          the value to set if not already set
   *
   * @return this instance for chaining
   */
  public static JazzContextManager putIfAbsent(final String key, final String valueIfAbsent,
      final Scope scope)
  {
    return putIfAbsent(key, () -> valueIfAbsent, scope);
  }

  /**
   * Set a public context property for {@code key} if the property is absent, invoking the provided
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
    return putIfAbsent(key, valueSupplier, Scope.PUBLIC);
  }

  /**
   * Set a context property in the requested scope for {@code key} if the property is absent,
   * invoking the provided {@code valueSupplier} to get the value to set.
   *
   * @param key
   *          the the non-{@code null} context key
   * @param valueSupplier
   *          a supplier to lazily provide the value if absent
   * @param scope
   *          the scope in which to set the property
   *
   * @return this instance for chaining
   */
  public static JazzContextManager putIfAbsent(final String key,
      final Supplier<String> valueSupplier, final Scope scope)
  {
    checkKey(key);

    final ContextProperty currentProp = context.get().get(key);

    if (currentProp == null || currentProp.getValue() == null)
    {
      context.get().put(key, new ContextProperty(valueSupplier.get(), scope));
    }

    return INSTANCE;
  }

  /**
   * Put all the given properties into the context as public properties.
   *
   * @param values
   *          the properties to add to the context
   */
  public static JazzContextManager putAll(@NonNull final Map<String, String> values)
  {
    return putAll(values, Scope.PUBLIC);
  }

  /**
   * Put all the given properties into the context in the requested scope.
   *
   * @param values
   *          the properties to add to the context
   * @param scope
   *          the scope in which to set the properties
   *
   * @return this instance for chaining
   */
  public static JazzContextManager putAll(@NonNull final Map<String, String> values,
      final Scope scope)
  {
    values.keySet().forEach(JazzContextManager::checkKey);

    final Map<String, ContextProperty> props = context.get();

    values.entrySet()
        .forEach(
            (entry) -> props.put(entry.getKey(),
                new ContextProperty(entry.getValue(), scope)));

    return INSTANCE;
  }

  /**
   * Put all the given properties into the context as public properties but only if they do not
   * already exist in the context.
   *
   * @param values
   *          the values to add to the context
   *
   * @return this instance for chaining
   */
  public static JazzContextManager putAllIfAbsent(@NonNull final Map<String, String> values)
  {
    return putAllIfAbsent(values, Scope.PUBLIC);
  }

  /**
   * Put all the given properties into the context with the requested scope but only if they do not
   * already exist in the context.
   *
   * @param values
   *          the values to add to the context
   * @param scope
   *          the scope in which to set the properties
   *
   * @return this instance for chaining
   */
  public static JazzContextManager putAllIfAbsent(@NonNull final Map<String, String> values,
      final Scope scope)
  {
    values.keySet().forEach(JazzContextManager::checkKey);

    final Map<String, ContextProperty> props = context.get();

    values.entrySet()
        .forEach((entry) ->
        {
          final ContextProperty currentProp = props.get(entry.getKey());

          if (currentProp == null || currentProp.getValue() == null)
          {
            props.put(entry.getKey(), new ContextProperty(entry.getValue(), scope));
          }
        });
    return INSTANCE;
  }

  /**
   * Removes the value for {@code key} if the value is absent.
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
   * Transform the current context into a mutable map which <em>excludes</em> values from the
   * private scope. The resulting map will not be backed by this instance. Modifications to the
   * returned map will not affect this context.
   */
  public static Map<String, String> toMap()
  {
    try
    {
      final Map<String, String> map = new HashMap<>();

      context.get().entrySet()
          .stream()
          .filter((entry) -> entry.getValue().getScope() == Scope.PUBLIC)
          .forEach((entry) ->
          {
            if (map.put(entry.getKey(), entry.getValue().getValue()) != null)
            {
              throw new IllegalStateException(String.format("Duplicate key %s", entry.getKey()));
            }
          });

      return map;
    }
    catch (final NullPointerException e)
    {
      log.error("Error converting context [{}] to map.", context.get());
      throw e;
    }
  }

  /**
   * Generates a (mostly) unique trace ID which will be used to track a request throughout our
   * infrastructure from one service to the next.
   */
  public static String generateTraceId()
  {
    return new BigInteger(JAZZ_TRACE_ID_RANDOM_BITS, ThreadLocalRandom.current())
        .add(BigInteger.valueOf(System.currentTimeMillis()))
        .toString(JAZZ_TRACE_ID_RADIX);
  }

  /**
   * Provides a Stream from all the values in the context.
   */
  public static Stream<Map.Entry<String, String>> stream()
  {
    return toMap().entrySet().stream();
  }

  private static HashMap<String, ContextProperty> createContext()
  {
    final HashMap<String, ContextProperty> newContext = new HashMap<>();

    newContext.put(JAZZ_CONTEXT_ID_KEY,
        new ContextProperty(
            RandomStringUtils.randomAlphanumeric(JAZZ_CONTEXT_ID_LENGTH),
            Scope.PRIVATE));

    return newContext;
  }

  static void checkKey(final String key)
  {
    Preconditions.checkArgument(JazzContextManager.KEY_PATTERN.matcher(key).matches(),
        "Invalid context key [%s], only numbers, letters, and . _ - characters allowed", key);
  }

  @RequiredArgsConstructor
  @Getter
  private static final class ContextProperty
  {
    private final String value;
    private final Scope scope;
  }

  public static enum Scope
  {
    PRIVATE,
    PUBLIC;
  }
}

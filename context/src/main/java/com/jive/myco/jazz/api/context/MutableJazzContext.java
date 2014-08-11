package com.jive.myco.jazz.api.context;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Version of a {@link JazzContext} that allows you to mutate the values in the context.
 *
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
public interface MutableJazzContext extends JazzContext
{

  /**
   * Set a context parameter.
   *
   * @param key
   *          the non-{@code null} context key
   * @param value
   *          the context value
   * @return this instance for chaining
   */
  MutableJazzContext put(String key, String value);

  /**
   * Set the value for {@code key} if the value is absent.
   *
   * @param key
   *          the the non-{@code null} context key
   * @param valueIfAbsent
   *          the value to set if not already set
   * @return this instance for chaining
   */
  MutableJazzContext putIfAbsent(String key, String valueIfAbsent);

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
  MutableJazzContext putIfAbsent(String key, Supplier<String> valueSupplier);

  /**
   * Put all the given values into the context.
   *
   * @param values
   *          the values to add to the context
   */
  MutableJazzContext putAll(Map<String, String> values);

  /**
   * Put all the given context values into this context.
   *
   * @param context
   *          existing context
   */
  MutableJazzContext putAll(JazzContext context);

  /**
   * Put all the given values into the context but only if they do not already exist in the context.
   *
   * @param values
   *          the values to add to the context
   * @return this instance for chaining
   */
  MutableJazzContext putAllIfAbsent(Map<String, String> values);

  /**
   * Clear all the context parameters.
   *
   * @return this instance for chaining
   */
  MutableJazzContext clear();
}

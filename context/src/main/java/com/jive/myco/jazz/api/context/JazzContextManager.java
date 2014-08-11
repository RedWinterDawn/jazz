package com.jive.myco.jazz.api.context;

import java.util.function.Supplier;

/**
 * Manager for the context in an application. Only 1 of these should be available in an application.
 *
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
public interface JazzContextManager
{
  /**
   * Clears the current context for this thread and initializes a new blank jazz context.
   *
   * @return the new jazz context
   */
  MutableJazzContext initializeRequestContext();

  /**
   * Clears the current context for this thread and initializes a new jazz context with all values
   * from the provided context.
   *
   * @param context
   *          the context to use for the request context
   * @return the new jazz context
   */
  MutableJazzContext initializeRequestContext(JazzContext context);

  /**
   * Get the context for the currently executing thread. This context represents the context
   * information that has been extracted from the incoming external request and any additional
   * context information that has been added and is passed through with the entire lifecycle of the
   * request.
   *
   * @return the current jazz context
   */
  MutableJazzContext getContext();

  /**
   * Get the immutable local context of the current system executing the request. This information
   * is static and will not change from one request to another.
   *
   * @return the local system context
   */
  JazzContext getLocalContext();

  /**
   * Get a context parameter from the local context first, then from the incoming context if not
   * available.
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
}

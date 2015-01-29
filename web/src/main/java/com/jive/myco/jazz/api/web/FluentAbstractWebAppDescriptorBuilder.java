package com.jive.myco.jazz.api.web;

import com.jive.myco.jazz.api.registry.AutoRegisteredServiceInstanceDescriptor;

/**
 * Describes the partial fluent builder API for constructing a {@link WebAppDescriptor}. The
 * {@code build()} method is explicitly omitted to facilitate the use of this builder API as part of
 * a larger DSL for configuring applications. Note that in this context, abstract indicates that the
 * builder does not presume to know where the value of fields such as sub-descriptors or certain
 * other pre-instantiated objects come from.
 *
 * @author David Valeri
 *
 * @param <T>
 *          the narrowed fluent builder sub-type returned from the fluent API
 */
public interface FluentAbstractWebAppDescriptorBuilder<T extends FluentAbstractWebAppDescriptorBuilder<T>>
{
  /**
   * The context path to publish the application under.
   *
   * @param contextPath
   *          the context path
   *
   * @return this builder for chaining
   */
  T contextPath(final String contextPath);

  /**
   * Sets an ID for the described application.
   *
   * @param id
   *          the ID to set
   *
   * @return this builder for chaining
   */
  T id(final String id);

  /**
   * Indicates of the application should automatically include a filter that enhances the Jazz
   * context with values from the request. Defaults to {@code true}.
   *
   * @param includeJazzContextFilter
   *          whether or not to include the filter
   *
   * @return this builder for chaining
   */
  T includeJazzContextFilter(
      final boolean includeJazzContextFilter);

  /**
   * Indicates of the application should automatically include a filter that enhances the MDC with
   * values from the Jazz context. Defaults to {@code true}.
   *
   * @param includeJazzMdcFilter
   *          whether or not to include the filter
   *
   * @return this builder for chaining
   */
  T includeJazzMdcFilter(final boolean includeJazzMdcFilter);

  /**
   * Indicates if the application should automatically include a filter that enhances the Jazz
   * context with values based on the application of the rules engine to the Jazz context. Defaults
   * to {@code true}.
   *
   * @param includeJazzContextEnhancerRulesFilter
   *          whether or not to include the filter
   *
   * @return this builder for chaining
   */
  T includeJazzContextEnhancerRulesFilter(final boolean includeJazzContextEnhancerRulesFilter);

  /**
   * Indicates if the application should automatically include a filter that enhances the Jazz
   * context with values based on HTTP request properties. Defaults to {@code true}.
   *
   * @param includeJazzHttpRequestContextFilter
   *          whether or not to include the filter
   *
   * @return this builder for chaining
   */
  T includeJazzHttpRequestContextFilter(final boolean includeJazzHttpRequestContextFilter);

  /**
   * Indicates the details of the application to automatically register in the service registry
   * after starting the application.
   *
   * @param autoRegisteredServiceInstanceDescriptor
   *          the descriptor containing the information to publish in the registry
   *
   * @return this builder for chaining
   */
  T register(final AutoRegisteredServiceInstanceDescriptor autoRegisteredServiceInstanceDescriptor);
}

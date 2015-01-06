package com.jive.myco.jazz.api.rest;

import com.jive.myco.jazz.api.core.network.ConnectorDescriptor;
import com.jive.myco.jazz.api.registry.AutoRegisteredServiceInstanceDescriptor;
import com.jive.myco.jazz.api.web.StaticResourceDescriptor;

/**
 * Describes the partial fluent builder API for constructing a {@link RestServiceDescriptor}
 * builder. The {@code build()} method is explicitly omitted to facilitate the use of this builder
 * API as part of a larger DSL for configuring applications. Note that in this context, abstract
 * indicates that the builder does not presume to know where the value of fields such as
 * sub-descriptors or certain other pre-instantiated objects come from.
 *
 * @author David Valeri
 *
 * @param <T>
 *          the narrowed fluent builder sub-type returned from the fluent API
 */
public interface FluentAbstractRestServiceDescriptorBuilder<T extends FluentAbstractRestServiceDescriptorBuilder<T>>
{
  /**
   * Sets an ID for the described service.
   *
   * @param id
   *          the ID to set
   *
   * @return this builder for chaining
   */
  T id(final String id);

  /**
   * Adds a static resource to the static resources provided via the descriptor.
   *
   * @param staticResourceDescriptor
   *          the resource to add
   *
   * @return this builder for chaining
   */
  T addStaticResource(
      final StaticResourceDescriptor staticResourceDescriptor);

  /**
   * Adds static resources to the static resources provided via the descriptor.
   *
   * @param staticResourceDescriptors
   *          the resources to add
   *
   * @return this builder for chaining
   */
  T addStaticResources(
      final Iterable<? extends StaticResourceDescriptor> staticResourceDescriptors);

  /**
   * Adds static resources to the static resources provided via the descriptor.
   *
   * @param staticResourceDescriptors
   *          the resources to add
   *
   * @return this builder for chaining
   */
  T addStaticResources(
      final StaticResourceDescriptor... staticResourceDescriptors);

  /**
   * Indicates of the service should automatically include a filter that enhances the Jazz context
   * with values from the request. Defaults to {@code true}.
   *
   * @param includeJazzContextFilter
   *          whether or not to include the filter
   *
   * @return this builder for chaining
   */
  T includeJazzContextFilter(
      final boolean includeJazzContextFilter);

  /**
   * Indicates of the service should automatically include a filter that enhances the MDC with
   * values from the Jazz context. Defaults to {@code true}.
   *
   * @param includeJazzMdcFilter
   *          whether or not to include the filter
   *
   * @return this builder for chaining
   */
  T includeJazzMdcFilter(final boolean includeJazzMdcFilter);

  /**
   * Indicates of the service should automatically include a filter that enhances the Jazz context
   * with values based on the application of the rules engine to the Jazz context. Defaults to
   * {@code true}.
   *
   * @param includeJazzContextEnhancerRulesFilter
   *          whether or not to include the filter
   *
   * @return this builder for chaining
   */
  T includeJazzContextEnhancerRulesFilter(
      final boolean includeJazzContextEnhancerRulesFilter);

  /**
   * A flag indicating if metrics annotations on resources should be parsed into their corresponding
   * metrics. Defaults to {@code true}.
   *
   * @param enableMetrics
   *          whether or not to enable annotation parsing
   *
   * @return this builder for chaining
   */
  T enableMetrics(final boolean enableMetrics);

  /**
   * Indicates if all JAX-RS resource methods exposed via this descriptor should be metered, timed,
   * and exception metered regardless of if they are annotated with the respective annotation.
   *
   * @param forceMetrics
   *          whether or not to force metrics on all methods
   *
   * @return this builder for chaining
   */
  T forceMetrics(final boolean forceMetrics);

  /**
   * Indicates the details of the service to automatically register in the service registry after
   * starting the service.
   *
   * @param autoRegisteredServiceInstanceDescriptor
   *          the descriptor containing the information to publish in the registry
   *
   * @return this builder for chaining
   */
  T register(final AutoRegisteredServiceInstanceDescriptor autoRegisteredServiceInstanceDescriptor);

  /**
   * The context path to publish the service under.
   *
   * @param contextPath
   *          the context path
   *
   * @return this builder for chaining
   */
  T contextPath(final String contextPath);

  /**
   * Adds a connector descriptor for a connector on which to bind the service.
   *
   * @param connectorDescriptor
   *          the descriptor
   *
   * @return this builder for chaining
   */
  T addConnector(final ConnectorDescriptor connectorDescriptor);
}

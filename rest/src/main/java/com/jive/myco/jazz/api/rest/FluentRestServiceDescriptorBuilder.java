package com.jive.myco.jazz.api.rest;

import javax.servlet.MultipartConfigElement;

import com.jive.myco.jazz.api.core.network.ConnectorDescriptor;
import com.jive.myco.jazz.api.registry.AutoRegisteredServiceInstanceDescriptor;
import com.jive.myco.jazz.api.web.FilterMappingDescriptor;
import com.jive.myco.jazz.api.web.StaticResourceDescriptor;

/**
 * Describes fluent builder API for constructing a {@link RestServiceDescriptor}. The
 * {@code build()} method is explicitly omitted to facilitate the use of this builder API as part of
 * a larger DSL for configuring applications. Unlike
 * {@link FluentAbstractRestServiceDescriptorBuilder}, this interface explicitly accepts
 * pre-instantiated sub-descriptors and pre-instantiated object.
 *
 * @author David Valeri
 *
 * @param <T>
 *          the narrowed fluent builder sub-type returned from the fluent API
 */
public interface FluentRestServiceDescriptorBuilder<T extends FluentRestServiceDescriptorBuilder<T>>
    extends FluentAbstractRestServiceDescriptorBuilder<T>
{
  /**
   * Adds a connector descriptor for a connector on which to bind the service.
   *
   * @param connectorDescriptor
   *          the descriptor
   *
   * @return this builder for chaining
   */
  T addConnector(final ConnectorDescriptor connectorDescriptor);

  /**
   * Add connector descriptors on which to bind the service.
   *
   * @param connectorDescriptors
   *          the descriptors
   *
   * @return this builder for chaining
   */
  T addConnectors(final Iterable<? extends ConnectorDescriptor> connectorDescriptors);

  /**
   * Add connector descriptors on which to bind the service.
   *
   * @param connectorDescriptors
   *          the descriptors
   *
   * @return this builder for chaining
   */
  T addConnectors(final ConnectorDescriptor... connectorDescriptors);

  /**
   * Adds a filter to the filters provided via the descriptor.
   * <p>
   * Filters are provided in the order in which they are added to this builder.
   *
   * @param filterDescriptor
   *          the filter to add
   *
   * @return this builder for chaining
   */
  T addFilter(final FilterMappingDescriptor filterDescriptor);

  /**
   * Adds a filter to the filters provided via the descriptor.
   *
   * @param filterDescriptors
   *          the filters to add
   *
   * @return this builder for chaining
   */
  T addFilters(
      final Iterable<? extends FilterMappingDescriptor> filterDescriptors);

  /**
   * Adds filters to the filters provided via the descriptor.
   *
   * @param filterDescriptors
   *          the filters to add
   *
   * @return this builder for chaining
   */
  T addFilters(final FilterMappingDescriptor... filterDescriptors);

  /**
   * Adds a singleton to the singletons provided via the descriptor.
   * <p>
   * If a singleton that {@link Object#equals(Object) equals} {@code singleton} has already been
   * configured, this call overwrites the previous value.
   * <p>
   * Singletons may be any JAX-RS related object including a REST resource or a {@code Provider}
   *
   * @param singleton
   *          the singleton JAX-RS resource to add
   *
   * @return this builder for chaining
   */
  T addSingleton(final Object singleton);

  /**
   * Adds singletons to the singletons provided via the descriptor.
   * <p>
   * If a singleton that {@link Object#equals(Object) equals} a value provided by {@code singletons}
   * has already been configured, this call overwrites the previous value.
   *
   * @param singletons
   *          the singletons to add
   *
   * @return this builder for chaining
   */
  T addSingletons(
      final Iterable<? extends Object> singletons);

  /**
   * Adds singletons to the singletons provided via the descriptor.
   * <p>
   * If a singleton that {@link Object#equals(Object) equals} a value provided by {@code singletons}
   * has already been configured, this call overwrites the previous value.
   *
   * @param singletons
   *          the singletons to add
   *
   * @return this builder for chaining
   */
  T addSingletons(
      final Object... singletons);

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
   * An optional multipart config element used to configure multi-part support on the REST service.
   *
   * @param multipartConfigElement
   *          the configuration to use
   *
   * @return this builder for chaining
   */
  T multipartConfigElement(
      final MultipartConfigElement multipartConfigElement);

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
}

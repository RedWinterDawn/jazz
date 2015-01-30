package com.jive.myco.jazz.api.web;

import com.jive.myco.jazz.api.core.network.ConnectorDescriptor;
import com.jive.myco.jazz.api.registry.AutoRegisteredServiceInstanceDescriptor;

/**
 * Describes fluent builder API for constructing a {@link WebAppDescriptor}. The {@code build()}
 * method is explicitly omitted to facilitate the use of this builder API as part of a larger DSL
 * for configuring applications. Unlike {@link FluentAbstractRestServiceDescriptorBuilder}, this
 * interface explicitly accepts pre-instantiated sub-descriptors and pre-instantiated object.
 *
 * @author David Valeri
 *
 * @param <T>
 *          the narrowed fluent builder sub-type returned from the fluent API
 */
public interface FluentWebAppDescriptorBuilder<T extends FluentWebAppDescriptorBuilder<T>> extends FluentAbstractWebAppDescriptorBuilder<T>
{
  /**
   * Adds a connector descriptor for a connector on which to bind the application.
   *
   * @param connectorDescriptor
   *          the descriptor
   *
   * @return this builder for chaining
   */
  T addConnector(final ConnectorDescriptor connectorDescriptor);

  /**
   * Add connector descriptors on which to bind the application.
   *
   * @param connectorDescriptors
   *          the descriptors
   *
   * @return this builder for chaining
   */
  T addConnectors(final Iterable<? extends ConnectorDescriptor> connectorDescriptors);

  /**
   * Add connector descriptors on which to bind the application.
   *
   * @param connectorDescriptors
   *          the descriptors
   *
   * @return this builder for chaining
   */
  T addConnectors(final ConnectorDescriptor... connectorDescriptors);

  /**
   * Adds a filter to the filters provided via the descriptor.
   *
   * @param filterMappingDescriptor
   *          the filter descriptor to add
   *
   * @return this builder for chaining
   */
  T addFilter(
      final FilterMappingDescriptor filterMappingDescriptor);

  /**
   * Adds a filter to the filters provided via the descriptor.
   *
   * @param filterMappingDescriptors
   *          the filters to add
   *
   * @return this builder for chaining
   */
  T addFilters(
      final Iterable<? extends FilterMappingDescriptor> filterMappingDescriptors);

  /**
   * Adds filters to the filters provided via the descriptor.
   *
   * @param filterMappingDescriptors
   *          the filters to add
   *
   * @return this builder for chaining
   */
  T addFilters(
      final FilterMappingDescriptor... filterMappingDescriptors);

  /**
   * Adds a servlet to the servlets provided via the descriptor.
   *
   * @param servletMappingDescriptor
   *          the descriptor for the servlet to add
   *
   * @return this builder for chaining
   */
  T addServlet(
      final ServletMappingDescriptor servletMappingDescriptor);

  /**
   * Adds a static resource to the static resources provided via the descriptor.
   *
   * @param staticResourceDescriptor
   *          pthe resource to add
   *
   * @return this builder for chaining
   */
  T addStaticResource(final StaticResourceDescriptor staticResourceDescriptor);

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
  T addStaticResources(final StaticResourceDescriptor... staticResourceDescriptors);

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

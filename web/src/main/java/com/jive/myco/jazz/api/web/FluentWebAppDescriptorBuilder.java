package com.jive.myco.jazz.api.web;


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
}

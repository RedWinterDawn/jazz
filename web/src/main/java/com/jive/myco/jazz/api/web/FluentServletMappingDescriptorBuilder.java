package com.jive.myco.jazz.api.web;

import javax.servlet.MultipartConfigElement;
import javax.servlet.Servlet;

/**
 * Describes fluent builder API for constructing a {@link ServletMappingDescriptor}. The
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
public interface FluentServletMappingDescriptorBuilder<T extends FluentServletMappingDescriptorBuilder<T>>
    extends FluentAbstractServletMappingDescriptorBuilder<T>
{
  /**
   * Sets the identifier for the servlet produced by this builder.
   *
   * @return this builder for chaining
   */
  T id(final String id);

  /**
   * Sets the URL pattern used to map requests to the filter in this descriptor.
   *
   * @return this builder for chaining
   */
  T urlPattern(final String urlPattern);

  /**
   * Sets an optional multipart config element used to configure multi-part support on the servlet.
   *
   * @return this builder for chaining
   */
  T multipartConfigElement(final MultipartConfigElement multipartConfigElement);

  /**
   * Sets the {@link Servlet} instance to use.
   *
   * @return this builder for chaining
   */
  T servlet(final Servlet servlet);
}

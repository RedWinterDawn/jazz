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
   * An optional multipart config element used to configure multi-part support on the servlet.
   */
  T multipartConfigElement(final MultipartConfigElement multipartConfigElement);

  T servlet(final Servlet servlet);
}

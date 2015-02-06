package com.jive.myco.jazz.api.web;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.MultipartConfigElement;
import javax.servlet.Servlet;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Builder;

/**
 * Represents a servlet and the URL pattern used to map requests to the servlet.
 *
 * @author David Valeri
 */
public final class ServletMappingDescriptor
{
  private final AtomicInteger INSTANCE_COUNTER = new AtomicInteger();

  /**
   * A descriptive ID for this servlet mapping.
   */
  @Getter
  private final String id;

  /**
   * The URL pattern used to map requests to the filter in this descriptor.
   */
  @Getter
  private final String urlPattern;

  /**
   * The servlet to use.
   */
  @Getter
  private final Servlet servlet;

  /**
   * An optional multipart config element used to configure multi-part support on the servlet.
   */
  @Getter
  private final MultipartConfigElement multipartConfigElement;

  /**
   * Creates a new instance.
   *
   * @param urlPattern
   *          the URL pattern used to map requests to the filter in this descriptor
   * @param servlet
   *          the servlet to use
   *
   * @deprecated use {@link #builder()} instead.
   */
  @Deprecated
  public ServletMappingDescriptor(final String urlPattern, final Servlet servlet)
  {
    this(null, urlPattern, servlet, null);
  }

  /**
   * Creates a new instance.
   *
   * @param urlPattern
   *          the URL pattern used to map requests to the filter in this descriptor
   * @param servlet
   *          the servlet to use
   * @param multipartConfigElement
   *          an optional multipart config element used to configure multi-part support on the
   *          servlet
   *
   * @deprecated use {@link #builder()} instead.
   */
  @Deprecated
  public ServletMappingDescriptor(final String urlPattern, final Servlet servlet,
      final MultipartConfigElement multipartConfigElement)
  {
    this(null, urlPattern, servlet, multipartConfigElement);
  }

  @Builder
  private ServletMappingDescriptor(
      final String id, @NonNull final String urlPattern, @NonNull final Servlet servlet,
      final MultipartConfigElement multipartConfigElement)
  {
    this.id = id == null ? "servlet-" + INSTANCE_COUNTER.getAndIncrement() : id;
    this.urlPattern = urlPattern;
    this.servlet = servlet;
    this.multipartConfigElement = multipartConfigElement;
  }

  public static final class ServletMappingDescriptorBuilder implements
      FluentServletMappingDescriptorBuilder<ServletMappingDescriptorBuilder>
  {
    // No-op, just ensuring that we implement the correct interface.
  }
}
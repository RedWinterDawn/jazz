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
@Getter
public final class ServletMappingDescriptor
{
  private final AtomicInteger INSTANCE_COUNTER = new AtomicInteger();

  /**
   * A descriptive ID for this servlet mapping.
   */
  private final String id;

  /**
   * The URL pattern used to map requests to the filter in this descriptor.
   */
  private final String urlPattern;

  /**
   * The servlet to use.
   */
  private final Servlet servlet;

  /**
   * An optional multipart config element used to configure multi-part support on the servlet.
   */
  private final MultipartConfigElement multipartConfigElement;

  public ServletMappingDescriptor(final String urlPattern, final Servlet servlet)
  {
    this(null, urlPattern, servlet, null);
  }

  public ServletMappingDescriptor(final String urlPattern, final Servlet servlet,
      final MultipartConfigElement multipartConfigElement)
  {
    this(null, urlPattern, servlet, multipartConfigElement);
  }

  @Builder
  public ServletMappingDescriptor(
      final String id, @NonNull final String urlPattern, @NonNull final Servlet servlet,
      final MultipartConfigElement multipartConfigElement)
  {
    this.id = id == null ? "servlet-" + INSTANCE_COUNTER.getAndIncrement() : id;
    this.urlPattern = urlPattern;
    this.servlet = servlet;
    this.multipartConfigElement = multipartConfigElement;
  }
}
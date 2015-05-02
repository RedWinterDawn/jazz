package com.jive.myco.jazz.api.web;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Filter;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * Represents a filter and the URL pattern used to map requests to the filter.
 *
 * @author David Valeri
 */
@Getter
public final class FilterMappingDescriptor
{
  private static final AtomicInteger COUNTER = new AtomicInteger();

  /**
   * A descriptive ID for this filter.
   */
  private final String id;

  /**
   * The URL pattern used to map requests to the filter in this descriptor.
   */
  private final String urlPattern;

  /**
   * The filter to use.
   */
  private final Filter filter;

  /**
   * Creates a new instance.
   *
   * @param urlPattern
   *          the URL pattern used to map requests to the filter in this descriptor
   * @param filter
   *          the filter to use
   *
   * @deprecated use {@link #builder} instead
   */
  @Deprecated
  public FilterMappingDescriptor(final String urlPattern, final Filter filter)
  {
    this(null, urlPattern, filter);
  }

  /**
   * Creates a new instance.
   *
   * @param id
   *          a descriptive ID for this filter
   * @param urlPattern
   *          the URL pattern used to map requests to the filter in this descriptor
   * @param filter
   *          the filter to use
   */
  @Builder
  private FilterMappingDescriptor(
      final String id, @NonNull final String urlPattern, @NonNull final Filter filter)
  {
    this.id = id == null ? "filter-" + COUNTER.getAndIncrement() : id;
    this.urlPattern = urlPattern;
    this.filter = filter;
  }

  public static final class FilterMappingDescriptorBuilder implements
      FluentFilterMappingDescriptorBuilder<FilterMappingDescriptorBuilder>
  {
    // No-op, just ensuring that we implement the correct interface.
  }
}

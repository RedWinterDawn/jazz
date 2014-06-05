package com.jive.myco.jazz.api.web;

import javax.servlet.Filter;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Builder;

/**
 * Represents a filter and the URL pattern used to map requests to the filter.
 *
 * @author David Valeri
 */
@RequiredArgsConstructor
@Getter
@Builder
public final class FilterMappingDescriptor
{
  /**
   * The URL pattern used to map requests to the filter in this descriptor.
   */
  @NonNull
  private final String urlPattern;

  /**
   * The filter to use.
   */
  @NonNull
  private final Filter filter;
}

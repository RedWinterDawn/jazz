package com.jive.myco.jazz.api.web;

import javax.servlet.Servlet;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Builder;

/**
 * Represents a servlet and the URL pattern used to map requests to the servlet.
 *
 * @author David Valeri
 */
@RequiredArgsConstructor
@Getter
@Builder
public final class ServletMappingDescriptor
{
  /**
   * The URL pattern used to map requests to the filter in this descriptor.
   */
  @NonNull
  private final String urlPattern;

  /**
   * The servlet to use.
   */
  @NonNull
  private final Servlet servlet;
}
package com.jive.myco.jazz.api.web;

import javax.servlet.Filter;

/**
 * Describes fluent builder API for constructing a {@link FilterMappingDescriptor}. The
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
public interface FluentFilterMappingDescriptorBuilder<T extends FluentFilterMappingDescriptorBuilder<T>>
    extends FluentAbstractFilterMappingDescriptorBuilder<T>
{
  /**
   * The filter to use.
   */
  T filter(final Filter filter);
}

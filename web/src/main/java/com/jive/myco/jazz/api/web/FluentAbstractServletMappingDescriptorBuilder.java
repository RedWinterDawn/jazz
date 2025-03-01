package com.jive.myco.jazz.api.web;

/**
 * Describes the partial fluent builder API for constructing a {@link ServletMappingDescriptor}. The
 * {@code build()} method is explicitly omitted to facilitate the use of this builder API as part of
 * a larger DSL for configuring applications. Note that in this context, abstract indicates that the
 * builder does not presume to know where the value of fields such as sub-descriptors or certain
 * other pre-instantiated objects come from.
 *
 * @author David Valeri
 *
 * @param <T>
 *          the narrowed fluent builder sub-type returned from the fluent API
 */
public interface FluentAbstractServletMappingDescriptorBuilder<T extends FluentAbstractServletMappingDescriptorBuilder<T>>
{
  // No-Op
}

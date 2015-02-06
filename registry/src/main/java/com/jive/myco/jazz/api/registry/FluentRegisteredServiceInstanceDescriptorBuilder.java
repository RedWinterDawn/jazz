package com.jive.myco.jazz.api.registry;

/**
 * Describes a fluent builder API for constructing a {@link ServiceInstanceDescriptor}. The
 * {@code build()} method is explicitly omitted to facilitate the use of this builder API as part of
 * a larger DSL for configuring applications.
 *
 * @author David Valeri
 *
 * @param <T>
 *          the narrowed fluent builder sub-type returned from the fluent API
 */
public interface FluentRegisteredServiceInstanceDescriptorBuilder<T extends FluentRegisteredServiceInstanceDescriptorBuilder<T>>
    extends FluentAutoRegisteredServiceInstanceDescriptorBuilder<T>
{
  T serviceAddress(final ServiceAddress serviceAddress);
}

package com.jive.myco.jazz.api.registry;

import java.util.Map;

import com.jive.myco.commons.versions.Version;

/**
 * Describes a fluent builder API for constructing a {@link AutoRegisteredServiceInstanceDescriptor}
 * . The {@code build()} method is explicitly omitted to facilitate the use of this builder API as
 * part of a larger DSL for configuring applications.
 *
 * @author David Valeri
 *
 * @param <T>
 *          the narrowed fluent builder sub-type returned from the fluent API
 */
public interface FluentAutoRegisteredServiceInstanceDescriptorBuilder<T extends FluentAutoRegisteredServiceInstanceDescriptorBuilder<T>>
{
  T serviceInterfaceName(
      final ServiceInterfaceName serviceInterfaceName);

  T serviceInterfaceVersion(
      final Version serviceInterfaceVersion);

  T properties(
      final Map<String, String> properties);

  T addProperty(final String key, final String value);

  T addProperties(final Map<String, String> properties);
}

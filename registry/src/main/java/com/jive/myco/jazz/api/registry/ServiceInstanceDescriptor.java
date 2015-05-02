package com.jive.myco.jazz.api.registry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import com.jive.myco.commons.versions.Version;

/**
 * Represents a service that is to be registered in the service registry. Create new instances via
 * the {@link #builder() builder}.
 *
 * @see com.jive.myco.jazz.api.registry.ServiceInterface
 * @see com.jive.myco.jazz.api.registry.ServiceInstanceId
 * @see com.jive.myco.jazz.api.registry.ServiceAddress
 * @see com.jive.myco.commons.versions.Version
 *
 * @author John Norton
 */
@Getter
@EqualsAndHashCode
public final class ServiceInstanceDescriptor
{
  @NonNull
  private final ServiceInterfaceName serviceInterfaceName;

  @NonNull
  private final ServiceAddress serviceAddress;

  private final Version serviceInterfaceVersion;

  private final Map<String, String> properties;

  @Builder
  private ServiceInstanceDescriptor(
      final ServiceInterfaceName serviceInterfaceName,
      final ServiceAddress serviceAddress,
      final Version serviceInterfaceVersion,
      final Map<String, String> properties)
  {
    this.serviceInterfaceName = serviceInterfaceName;
    this.serviceAddress = serviceAddress;
    this.serviceInterfaceVersion = serviceInterfaceVersion;
    this.properties = Collections.unmodifiableMap(new HashMap<>(properties));
  }

  public static final class ServiceInstanceDescriptorBuilder implements
      FluentRegisteredServiceInstanceDescriptorBuilder<ServiceInstanceDescriptorBuilder>
  {
    private final Map<String, String> properties = new HashMap<>();

    @Override
    public ServiceInstanceDescriptorBuilder addProperty(final String key, final String value)
    {
      properties.put(key, value);
      return this;
    }

    @Override
    public ServiceInstanceDescriptorBuilder addProperties(final Map<String, String> properties)
    {
      this.properties.putAll(properties);
      return this;
    }

    @Override
    public ServiceInstanceDescriptorBuilder properties(final Map<String, String> properties)
    {
      this.properties.clear();
      addProperties(properties);
      return this;
    }
  }
}

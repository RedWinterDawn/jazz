package com.jive.myco.jazz.api.registry;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Builder;

import com.jive.myco.commons.versions.Version;

/**
 * A descriptor used to register a service instance automatically while creating the service
 * instance. In this scenario, the service address is not known until the instance is created.
 *
 * @author David Valeri
 */
@Builder
@Getter
public class AutoRegisteredServiceInstanceDescriptor
{
  @NonNull
  private final ServiceInterfaceName serviceInterfaceName;

  private final Version serviceInterfaceVersion;

  private final Map<String, String> properties;

  public static final class AutoRegisteredServiceInstanceDescriptorBuilder
      implements
      FluentAutoRegisteredServiceInstanceDescriptorBuilder<AutoRegisteredServiceInstanceDescriptorBuilder>
  {
    private final Map<String, String> properties = new HashMap<>();

    @Override
    public AutoRegisteredServiceInstanceDescriptorBuilder addProperty(final String key,
        final String value)
    {
      properties.put(key, value);
      return this;
    }

    @Override
    public AutoRegisteredServiceInstanceDescriptorBuilder addProperties(
        final Map<String, String> properties)
    {
      this.properties.putAll(properties);
      return this;
    }

    @Override
    public AutoRegisteredServiceInstanceDescriptorBuilder properties(
        final Map<String, String> properties)
    {
      properties.clear();
      addProperties(properties);
      return this;
    }
  }
}

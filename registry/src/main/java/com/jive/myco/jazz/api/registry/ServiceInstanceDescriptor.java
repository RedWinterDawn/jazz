package com.jive.myco.jazz.api.registry;

import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Builder;

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
@Builder
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
}

package com.jive.myco.jazz.api.registry;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Builder;

import com.jive.myco.commons.versions.Version;

/**
 *
 * Represents a service interface in the registry. Used for searching or registering service
 * instances within the registry.
 *
 * @see com.jive.myco.jazz.api.registry.ServiceInterfaceName
 * @see Version
 *
 * @author John Norton
 */
@Builder
@Getter
@EqualsAndHashCode
public final class ServiceInterface
{
  @NonNull
  private final ServiceInterfaceName serviceInterfaceName;
  @NonNull
  private final Version version;

  @Override
  public String toString()
  {
    return String.format("%s:%s", serviceInterfaceName.toString(), version.getMajor());
  }
}

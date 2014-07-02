package com.jive.myco.jazz.api.registry;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Builder;

import com.jive.myco.commons.versions.Version;

/**
 * @author John Norton
 */

@Builder
@Getter
public class ServiceInterface
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

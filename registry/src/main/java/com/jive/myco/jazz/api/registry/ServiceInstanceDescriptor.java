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
public class ServiceInstanceDescriptor
{

  @NonNull
  private final ServiceInterfaceName serviceInterfaceName;
  @NonNull
  private final ServiceInstanceId serviceInstanceId;
  @NonNull
  private final ServiceAddress serviceAddress;
  @NonNull
  private final Version serviceInterfaceVersion;
}

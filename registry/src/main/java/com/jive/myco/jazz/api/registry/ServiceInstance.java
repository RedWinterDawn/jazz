package com.jive.myco.jazz.api.registry;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Builder;

import com.jive.myco.commons.versions.Version;
import com.jive.myco.jazz.api.core.coordinates.Locality;

/**
 *
 * Represents a registered service in the service registry.
 *
 * @see com.jive.myco.jazz.api.registry.ServiceInterface
 * @see com.jive.myco.jazz.api.registry.ServiceAddress
 * @see com.jive.myco.commons.versions.Version
 * @see com.jive.myco.jazz.api.core.coordinates.Locality
 *
 *
 * @author John Norton
 */

@Builder
@Getter
public class ServiceInstance
{

  @NonNull
  private final ServiceInterface serviceInterface;
  @NonNull
  private final ServiceInstanceId serviceInstanceId;
  @NonNull
  private final ServiceAddress serviceAddress;
  @NonNull
  private final Locality locality;

}

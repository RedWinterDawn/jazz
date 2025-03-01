package com.jive.myco.jazz.api.registry;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Represents an address of the service. Any string valid except null.
 *
 * @author John Norton
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public final class ServiceAddress
{
  @NonNull
  private final String protocol;

  @NonNull
  private final String address;
}

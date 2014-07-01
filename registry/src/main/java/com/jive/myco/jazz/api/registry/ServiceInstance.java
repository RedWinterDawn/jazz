package com.jive.myco.jazz.api.registry;

/**
 * @author John Norton
 */


import java.util.Properties;

import com.jive.myco.commons.versions.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;

import com.jive.myco.jazz.api.core.coordinates.Locality;

@Getter
@AllArgsConstructor
public class ServiceInstance {
  private Version version;
  private Properties serviceProperties;
  private String instanceId;
  private Locality locality;
  private ServiceAddress serviceAddress;
}

package com.jive.myco.jazz.api.registry;

/**
 * Created by jnorton on 6/30/14.
 */




import com.jive.myco.jazz.api.core.coordinates.Locality;
import lombok.Data;

import java.util.Map;

/**
 * The type Service instance.
 */
@Data
public class ServiceInstance {
  private String version;
  private Map<String, String> serviceProoperties;
  private String instanceId;
  private Locality locality;
  private ServiceAddress serviceAddress;
}

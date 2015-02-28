package com.jive.myco.jazz.api.registry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.jive.myco.commons.versions.Version;
import com.jive.myco.commons.versions.VersionRange;

/**
 * Indicates that an interface represents an entry point to a Jazz Service.
 *
 * @author David Valeri
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface JazzService
{
  /**
   * A required field indicating the identifier for the service interface name. Must be a valid
   * {@link ServiceInterfaceName} value.
   */
  public String serviceInterfaceName();

  /**
   * A required field indicating the version of the service interface. Must be a valid
   * {@link Version} or {@link VersionRange} value.
   */
  public String serviceInterfaceVersion();

  /**
   * An optional field indicating the protocol used to interact with the service described by the
   * annotated interface.
   */
  public String protocol();
}

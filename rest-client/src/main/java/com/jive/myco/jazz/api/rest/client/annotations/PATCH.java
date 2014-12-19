package com.jive.myco.jazz.api.rest.client.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.HttpMethod;

/**
 * A custom annotation used to indicate that the HTTP command is patch.
 *
 * @author David Valeri
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@HttpMethod("PATCH")
@Documented
public @interface PATCH
{
  // No-op
}

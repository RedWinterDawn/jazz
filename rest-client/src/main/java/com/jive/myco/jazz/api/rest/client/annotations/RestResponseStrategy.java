package com.jive.myco.jazz.api.rest.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation signaling that the rest client should apply a special response handling strategy.
 *
 * Can appear on both types and methods.
 *
 * @author Binh Tran
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface RestResponseStrategy
{
  int[] expectedStatus() default { 200, 201, 202, 203, 204, 205, 206 };

  int maxRetries() default 0;
}

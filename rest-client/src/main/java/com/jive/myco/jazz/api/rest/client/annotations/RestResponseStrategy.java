package com.jive.myco.jazz.api.rest.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation signaling that the rest client need special error handling strategy.
 *
 * Can appear on both interface and function.
 *
 * @author Binh Tran
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD})
public @interface RestResponseStrategy
{
  static final String DEFAULT_HANDLER = "default";
  static final String NO_RETRY = "no-retry";

  /**
   * If specified, this value indicates that there's a bounded instance of RestResponseStrategy and
   *   that will be used to handle error if occur from this endpoint
   * @return the name of a bound instance of RestResponseStrategy
   */
  String handlerName() default DEFAULT_HANDLER;

  int[] expectedStatus() default {200, 204};

  int maxRetries() default 5;
}

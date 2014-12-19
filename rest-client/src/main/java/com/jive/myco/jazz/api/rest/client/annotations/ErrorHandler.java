package com.jive.myco.jazz.api.rest.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation signalling that the rest client need special error handling strategy.
 *
 * Can appear on both interface and function.
 *
 * Created by btran on 12/19/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD})
public @interface ErrorHandler
{
  static final String DEFAULT_HANDLER = "default";
  static final String NO_RETRY = "no-retry";

  /**
   * If specified, this value indicates that there's a bounded instance of RestExceptionHandler and
   *   that will be used to handle error if occur from this endpoint
   * @return the name of a bound instance of RestExceptionHandler
   */
  String value() default DEFAULT_HANDLER;
}

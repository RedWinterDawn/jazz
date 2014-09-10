package com.jive.myco.jazz.api.rest.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface MultipartFormParam
{
  /**
   * Defines the name of a "multipart/form-data" body part whose content will be populated via the
   * value of the annotated method argument.
   *
   * @return the name of a "multipart/form-data" body part
   */
  String value();

  /**
   * Defines the media type of a "multipart/form-data" body part whose content will be populated via
   * the value of the annotated method argument.
   *
   * @return the media type of a "multipart/form-data" body part
   */
  String mediaType();
}

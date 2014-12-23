package com.jive.myco.jazz.api.rest.client.ExceptionHandler;

import java.util.function.Function;

/**
 * @author Binh Tran
 * @author Rich Adams
 */
public interface RestExceptionHandlingResult
{
  String getBaseUrl();

  int getRetryCount();

  public static final Function<String, RestExceptionHandlingResult> firstResultBuilder = (s) ->
      new RestExceptionHandlingResult() {

        @Override
        public String getBaseUrl()
        {
          return s;
        }

        @Override
        public int getRetryCount()
        {
          return 0;
        }
      };
}

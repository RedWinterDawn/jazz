package com.jive.myco.jazz.api.rest.client.exceptionhandler;

/**
 * Created by btran on 12/20/14.
 */
@FunctionalInterface
public interface RestExceptionHandlerProvider
{
  RestExceptionHandler getHandler(String name);
}

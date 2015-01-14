package com.jive.myco.jazz.api.rest.client.responsehandler;

/**
 * @author Binh Tran
 */
@FunctionalInterface
public interface RestResponseHandlerProvider
{
  RestResponseHandler getHandler(String name);
}

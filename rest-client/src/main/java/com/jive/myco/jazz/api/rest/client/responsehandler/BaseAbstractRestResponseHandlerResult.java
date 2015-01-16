package com.jive.myco.jazz.api.rest.client.responsehandler;

/**
* @author Binh Tran
*/
class BaseAbstractRestResponseHandlerResult extends AbstractRestResponseHandlerResult
{

  protected BaseAbstractRestResponseHandlerResult(final String baseUrl, final int retryCount)
  {
    super(baseUrl, retryCount);
  }
}

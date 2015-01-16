package com.jive.myco.jazz.api.rest.client.responsehandler;

/**
 * @author Binh Tran
 * @author Rich Adams
 */
public class DeserializeRestReponseHandlerResult implements RestResponseHandlerResult
{
  private DeserializeRestReponseHandlerResult(){}

  public static DeserializeRestReponseHandlerResult INSTANCE =
      new DeserializeRestReponseHandlerResult();
}

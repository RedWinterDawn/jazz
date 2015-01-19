package com.jive.myco.jazz.api.rest.client.responsehandler;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author David Valeri
 */
@RequiredArgsConstructor
@Getter
public class RetriesExhaustedRestResponseHandlerResult extends AbstractRestResponseHandlerResult
{
  @NonNull
  private final Exception exception;
}

package com.jive.myco.jazz.api.rest.client.responsehandler;

import java.util.function.Supplier;

import lombok.Getter;
import lombok.NonNull;

/**
 * @author Binh Tran
 * @author Rich Adams
 */
@Getter
public class RetryRestResponseHandlerResult extends AbstractRestResponseHandlerResult
{
  private final Supplier<String> baseUrlSupplier;
  private final Exception exception;

  public RetryRestResponseHandlerResult(@NonNull final Supplier<String> baseUrlSupplier,
      @NonNull final Exception exception)
  {
    this.baseUrlSupplier = baseUrlSupplier;
    this.exception = exception;
  }
}

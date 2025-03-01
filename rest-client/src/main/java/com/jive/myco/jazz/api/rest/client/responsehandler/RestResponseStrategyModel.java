package com.jive.myco.jazz.api.rest.client.responsehandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.ToString;

import com.jive.myco.jazz.api.rest.client.annotations.RestResponseStrategy;

/**
 * @author David Valeri
 */
@Getter
@ToString
public class RestResponseStrategyModel
{
  private static final String DEFAULT_REST_RESPONSE_HANDLER_KEY = "default";

  private final Set<Integer> expectedResponseCodes;
  private final int maxRetries;
  private final String restResponseHandlerKey = DEFAULT_REST_RESPONSE_HANDLER_KEY;
  private final boolean allowAll2xxResponseCodes;

  public RestResponseStrategyModel(final RestResponseStrategy annotation)
  {
    final int[] values = annotation.expectedStatus();

    if (values != null)
    {
      final Set<Integer> codes = new HashSet<>(values.length);
      for (final Integer value : values)
      {
        codes.add(value);
      }

      this.expectedResponseCodes = Collections.unmodifiableSet(codes);
    }
    else
    {
      this.expectedResponseCodes = Collections.emptySet();
    }

    this.maxRetries = annotation.maxRetries();
    this.allowAll2xxResponseCodes = annotation.allowAll2xxResponseCodes();
  }

  public RestResponseStrategyModel(final Set<Integer> expectedResponseCodes,
      final boolean allowAll2xxResponseCodes)
  {
    this.expectedResponseCodes =
        expectedResponseCodes == null ?
            Collections.emptySet() :
            Collections.unmodifiableSet(new HashSet<>(expectedResponseCodes));

    this.maxRetries = 0;
    this.allowAll2xxResponseCodes = allowAll2xxResponseCodes;
  }
}

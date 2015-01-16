package com.jive.myco.jazz.api.rest.client.responsehandler;

import java.util.Arrays;
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
  private static final Set<Integer> DEFAULT_EXPECTED_RESPONSE_CODES =
      Collections
          .unmodifiableSet(
          new HashSet<>(
              Arrays.asList(200, 202)));

  private final Set<Integer> expectedResponseCodes;
  private final int maxRetries;
  private final String restResponseHandlerKey = DEFAULT_REST_RESPONSE_HANDLER_KEY;

  public RestResponseStrategyModel()
  {
    this((Set<Integer>) null);
  }

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
  }

  public RestResponseStrategyModel(final Set<Integer> expectedResponseCodes)
  {
    this.expectedResponseCodes =
        expectedResponseCodes == null ?
            DEFAULT_EXPECTED_RESPONSE_CODES :
            Collections
                .unmodifiableSet(
                    new HashSet<>(expectedResponseCodes));

    this.maxRetries = 0;
  }
}

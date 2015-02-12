package com.jive.myco.jazz.api.rest.client;

import java.beans.ConstructorProperties;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Builder;

/**
 * A HTTP response object that can be used to get additional information about a response from an
 * HTTP request made using a REST client.
 *
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
@ToString(exclude = "content")
@Getter
public class RestClientHttpResponse<T>
{
  private final int responseCode;

  private final String reasonPhrase;

  private final T content;

  private final Map<String, List<String>> headers;

  @Builder
  @ConstructorProperties({ "responseCode", "reasonPhrase", "content", "headers" })
  public RestClientHttpResponse(final int responseCode, final String reasonPhrase, final T content,
      final Map<String, List<String>> headers)
  {
    this.responseCode = responseCode;
    this.reasonPhrase = reasonPhrase;
    this.content = content;
    this.headers = headers == null ? Collections.emptyMap() : headers;
  }

  public Map<String, List<String>> getHeaders()
  {
    return Collections.unmodifiableMap(headers);
  }
}

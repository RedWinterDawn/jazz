package com.jive.myco.jazz.api.rest.client;

import java.util.concurrent.TimeUnit;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import com.google.common.net.HostAndPort;

/**
 * A descriptor for creating {@link RestClientFactory} instances via a
 * {@link RestClientFactoryManager}.
 *
 * @author David Valeri
 */
@Builder
@Getter
public class RestClientFactoryDescriptor
{
  @NonNull
  private final String id;

  /**
   * The host and port for the HTTP proxy that traffic should be directed through.
   */
  private final HostAndPort httpProxy;

  /**
   * The time, in {@link #getConnectTimeoutUnit()}s, before a connection attempt is abandoned.
   * {@code null} indicates to use the system default while {@code 0} indicates an infinite timeout.
   * Defaults to the system default.
   */
  private final Long connectTimeout;

  /**
   * The time unit for {@link #getConnectTimeout()}. Defaults to {@link TimeUnit#MILLISECONDS}.
   */
  @NonNull
  private final TimeUnit connectTimeoutUnit;

  /**
   * The time, in {@link #getSocketTimeoutUnit()}s, before a socket is timed out {@code SO_TIMEOUT}.
   * {@code null} indicates to use the system default while {@code 0} indicates an infinite timeout.
   * Defaults to the system default.
   */
  private final Long socketTimeout;

  /**
   * The time unit for {@link #getSocketTimeout()}. Defaults to {@link TimeUnit#MILLISECONDS}.
   */
  @NonNull
  private final TimeUnit socketTimeoutUnit;

  /**
   * The maximum number of redirects allowed when {@link #isRedirectsEnabled()} is {@code true}.
   * Defaults to {@code 5}.
   */
  private final int maxRedirects;

  /**
   * Indicates if redirects will be followed. Defaults to {@code true}.
   */
  private final boolean redirectsEnabled;

  /**
   * Indicates if relative URLs in redirects will be followed. Defaults to {@code true}.
   */
  private final boolean relativeRedirectsAllowed;

  /**
   * Indicates if connections can be reused (TCP connection pooling).
   */
  private final boolean reuseConnections;

  /**
   * The cookie specification to enable on the underlying HTTP client. Defaults to an RFC 6265
   * compliant spec.
   */
  private final CookieSpec cookieSpec;

  public static final class RestClientFactoryDescriptorBuilder
  {
    private TimeUnit connectTimeoutUnit = TimeUnit.MILLISECONDS;

    private TimeUnit socketTimeoutUnit = TimeUnit.MILLISECONDS;

    private int maxRedirects = 5;

    private boolean redirectsEnabled = true;

    private boolean relativeRedirectsAllowed = true;

    private boolean reuseConnections = false;

    private CookieSpec cookieSpec = CookieSpec.STANDARD;
  }
}

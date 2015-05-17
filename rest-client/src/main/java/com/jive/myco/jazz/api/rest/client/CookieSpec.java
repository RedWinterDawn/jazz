package com.jive.myco.jazz.api.rest.client;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Brandon Pedersen
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CookieSpec
{
  /**
   * The RFC 6265 compliant policy (interoprability profile).
   */
  public static final CookieSpec STANDARD = CookieSpec.valueOf("standard");

  /**
   * The Netscape cookie draft compliant policy.
   */
  public static final CookieSpec NETSCAPE = CookieSpec.valueOf("netscape");

  @Getter
  private final String value;

  public static CookieSpec valueOf(@NonNull final String value)
  {
    return new CookieSpec(value);
  }

  @Override
  public String toString()
  {
    return value;
  }
}

package com.jive.myco.jazz.api.core.coordinates;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for {@link ProviderId}.
 *
 * @author David Valeri
 */
public class ProviderIdTest
{
  @Test
  public void testProviderId()
  {
    assertEquals("a", ProviderId.valueOf("a").getId());
    assertEquals("z", ProviderId.valueOf("z").getId());
    assertEquals("A", ProviderId.valueOf("A").getId());
    assertEquals("Z", ProviderId.valueOf("Z").getId());
    assertEquals("0", ProviderId.valueOf("0").getId());
    assertEquals("9", ProviderId.valueOf("9").getId());

    try
    {
      ProviderId.valueOf("-");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      ProviderId.valueOf(" ");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      ProviderId.valueOf("");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      ProviderId.valueOf(null);
      fail();
    }
    catch (final NullPointerException e)
    {
      // expected
    }
  }
}

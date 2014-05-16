package com.jive.myco.jazz.core.coordinates;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for {@link Coordinates} and its constituent components.
 *
 * @author David Valeri
 */
public class CoordinatesTest
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

  @Test
  public void testInstanceId()
  {
    assertEquals("a", InstanceId.valueOf("a").getId());
    assertEquals("z", InstanceId.valueOf("z").getId());
    assertEquals("A", InstanceId.valueOf("A").getId());
    assertEquals("Z", InstanceId.valueOf("Z").getId());
    assertEquals("0", InstanceId.valueOf("0").getId());
    assertEquals("9", InstanceId.valueOf("9").getId());

    try
    {
      InstanceId.valueOf("-");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      InstanceId.valueOf(" ");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      InstanceId.valueOf("");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      InstanceId.valueOf(null);
      fail();
    }
    catch (final NullPointerException e)
    {
      // expected
    }
  }

  @Test
  public void testCoordinates()
  {
    final Coordinates coordinates = Coordinates.valueOf("A:pvu-1a:B");

    assertEquals("A", coordinates.getProvider().toString());
    assertEquals("pvu-1a", coordinates.getLocality().toString());
    assertEquals("B", coordinates.getInstance().toString());

    try
    {
      Coordinates.valueOf(null);
      fail();
    }
    catch (final NullPointerException e)
    {
      // expected
    }

    try
    {
      Coordinates.valueOf("pvu-1a:B");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      Coordinates.valueOf("A:vu-1a:B");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      Coordinates.valueOf("A:pvu-1a");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      Coordinates.valueOf("A:pvu-1a:!");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }
  }
}

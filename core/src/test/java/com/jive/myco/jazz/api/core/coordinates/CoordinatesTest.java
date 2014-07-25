package com.jive.myco.jazz.api.core.coordinates;

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
  public void testCoordinates()
  {
    final Coordinates coordinates = Coordinates.valueOf("A:pvu-1a:blah-blah-blah:B");

    assertEquals("A", coordinates.getProvider().toString());
    assertEquals("pvu-1a", coordinates.getLocality().toString());
    assertEquals("blah-blah-blah", coordinates.getInstanceType().toString());
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
      Coordinates.valueOf("blah:pvu-1a:B");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      Coordinates.valueOf("A:blah:vu-1a:B");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      Coordinates.valueOf("A:blah:pvu-1a");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      Coordinates.valueOf("A:blah:pvu-1a:!");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }
  }
}

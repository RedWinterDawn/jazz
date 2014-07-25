package com.jive.myco.jazz.api.core.coordinates;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for {{@link InstanceId}.
 *
 * @author David Valeri
 */
public class InstanceIdTest
{
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
}

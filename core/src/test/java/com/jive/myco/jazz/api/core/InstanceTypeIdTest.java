package com.jive.myco.jazz.api.core;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jive.myco.jazz.api.core.InstanceTypeId;

/**
 * Tests for {@link InstanceTypeId}.
 *
 * @author David Valeri
 */
public class InstanceTypeIdTest
{
  @Test
  public void test()
  {
    assertEquals("a", InstanceTypeId.valueOf("a").getId());
    assertEquals("z", InstanceTypeId.valueOf("z").getId());
    assertEquals("A", InstanceTypeId.valueOf("A").getId());
    assertEquals("Z", InstanceTypeId.valueOf("Z").getId());
    assertEquals("0", InstanceTypeId.valueOf("0").getId());
    assertEquals("9", InstanceTypeId.valueOf("9").getId());

    try
    {
      InstanceTypeId.valueOf(" ");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      InstanceTypeId.valueOf("");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      InstanceTypeId.valueOf(null);
      fail();
    }
    catch (final NullPointerException e)
    {
      // expected
    }
  }
}

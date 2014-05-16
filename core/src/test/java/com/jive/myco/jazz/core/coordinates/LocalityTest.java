package com.jive.myco.jazz.core.coordinates;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jive.myco.jazz.api.core.coordinates.ClusterId;
import com.jive.myco.jazz.api.core.coordinates.DatacenterId;
import com.jive.myco.jazz.api.core.coordinates.Locality;
import com.jive.myco.jazz.api.core.coordinates.RegionId;

/**
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
public class LocalityTest
{
  @Test
  public void testClusterId() throws Exception
  {
    assertEquals("a", ClusterId.valueOf("a").getId());
    assertEquals("z", ClusterId.valueOf("z").getId());

    try
    {
      ClusterId.valueOf("1");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      ClusterId.valueOf("ab");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      ClusterId.valueOf("");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      ClusterId.valueOf(null);
      fail();
    }
    catch (final NullPointerException e)
    {
      // expected
    }

    try
    {
      ClusterId.valueOf("-a-");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }
  }

  @Test
  public void testDatacenterId() throws Exception
  {
    assertEquals("0", DatacenterId.valueOf("0").getId());
    assertEquals("a", DatacenterId.valueOf("a").getId());
    assertEquals("aa", DatacenterId.valueOf("aa").getId());
    assertEquals("0a", DatacenterId.valueOf("0a").getId());
    assertEquals("1b", DatacenterId.valueOf("1b").getId());
    assertEquals("01", DatacenterId.valueOf("01").getId());

    try
    {
      DatacenterId.valueOf("_.j");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      DatacenterId.valueOf("skjd_-l");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      DatacenterId.valueOf("1-a");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      DatacenterId.valueOf("");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      DatacenterId.valueOf(null);
      fail();
    }
    catch (final NullPointerException e)
    {
      // expected
    }
  }

  @Test
  public void testRegionId() throws Exception
  {
    assertEquals("us_west_1", RegionId.valueOf("us_west_1").getId());
    assertEquals("pvu", RegionId.valueOf("pvu").getId());
    assertEquals("lax", RegionId.valueOf("lax").getId());

    try
    {
      RegionId.valueOf("-");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      RegionId.valueOf("_");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      RegionId.valueOf("west_1_");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      RegionId.valueOf("_east");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      RegionId.valueOf("_.j");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      RegionId.valueOf("jds_s-dh");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      RegionId.valueOf("west 1");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      RegionId.valueOf("");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      RegionId.valueOf(null);
      fail();
    }
    catch (final NullPointerException e)
    {
      // expected
    }
  }

  @Test
  public void testLocality() throws Exception
  {
    Locality locality = Locality.valueOf("us_west_1-ba");
    assertEquals("us_west_1-ba", locality.toString());
    assertEquals("us_west_1", locality.getRegion().getId());
    assertEquals("b", locality.getDatacenter().getId());
    assertEquals("a", locality.getCluster().getId());

    locality = Locality.valueOf("pvu-1b");
    assertEquals("pvu-1b", locality.toString());
    assertEquals("pvu", locality.getRegion().getId());
    assertEquals("1", locality.getDatacenter().getId());
    assertEquals("b", locality.getCluster().getId());

    try
    {
      Locality.valueOf("us-west-1a");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      Locality.valueOf("us-west-1aa");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      Locality.valueOf("us-west");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      Locality.valueOf("us-west-1a1");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      Locality.valueOf("lax-a1");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      Locality.valueOf("");
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      // expected
    }

    try
    {
      Locality.valueOf(null);
      fail();
    }
    catch (final NullPointerException e)
    {
      // expected
    }
  }

  @Test
  public void testSame()
  {
    final Locality control = Locality.valueOf("pvu-1b");

    assertTrue(control.sameCluster(Locality.valueOf("pvu-1b")));
    assertFalse(control.sameCluster(Locality.valueOf("pvu-1a")));
    assertFalse(control.sameCluster(Locality.valueOf("pvu-2b")));
    assertFalse(control.sameCluster(Locality.valueOf("lax-1b")));

    assertTrue(control.sameDatacenter(Locality.valueOf("pvu-1b")));
    assertTrue(control.sameDatacenter(Locality.valueOf("pvu-1c")));
    assertFalse(control.sameDatacenter(Locality.valueOf("pvu-2b")));
    assertFalse(control.sameDatacenter(Locality.valueOf("lax-1b")));

    assertTrue(control.sameRegion(Locality.valueOf("pvu-1b")));
    assertTrue(control.sameRegion(Locality.valueOf("pvu-2b")));
    assertTrue(control.sameRegion(Locality.valueOf("pvu-2d")));
    assertFalse(control.sameRegion(Locality.valueOf("lax-1b")));

  }

}

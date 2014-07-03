package com.jive.myco.jazz.api.core.coordinates;

import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

/**
 * Represents a region where multiple sets of {@link DatacenterId datacenters} are located together.
 * This is the highest level in the locality hierarchy.
 * <p>
 * For Jive's hardware this represents the region's three letter airport code. For something like
 * EC2 this will represent the AWS region ID (with dashes replaced by underscores). Valid characters
 * include a-z, 0-9, and _.
 *
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegionId
{
  protected static final Pattern REGION_ID_PATTERN =
      Pattern.compile("[a-z,0-9]([a-z,0-9,_]+[a-z,0-9])?");

  private final String id;

  @Override
  public String toString()
  {
    return id;
  }

  public static RegionId valueOf(@NonNull final String id)
  {
    if (!REGION_ID_PATTERN.matcher(id).matches())
    {
      throw new IllegalArgumentException(id + " is not a valid region ID");
    }

    return new RegionId(id);
  }
}

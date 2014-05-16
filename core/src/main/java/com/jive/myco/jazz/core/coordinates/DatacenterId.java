package com.jive.myco.jazz.core.coordinates;

import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Represents a datacenter within a region.
 * <p>
 * For Jive hardware this will be a single digit [0-9]. For something like EC2 this represents the
 * availability zone in the AWS region which is a single letter [a-z].
 *
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class DatacenterId
{
  protected static final Pattern DATACENTER_ID_PATTERN = Pattern.compile("[a-z,0-9]+");

  private final String id;

  public static DatacenterId valueOf(final String id)
  {
    if (!DATACENTER_ID_PATTERN.matcher(id).matches())
    {
      throw new IllegalArgumentException(id + " is not a valid datacenter ID");
    }

    return new DatacenterId(id);
  }
}

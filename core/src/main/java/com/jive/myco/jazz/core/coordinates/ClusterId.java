package com.jive.myco.jazz.core.coordinates;

import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

/**
 * A cluster represents a single stack of our services running within a datacenter.
 * <p>
 * On Jive's hardware this represents a single cluster. With another provider like EC2 this will
 * identify a stack of our services running within the {@link DatacenterId datacenter} or
 * availability zone.
 * <p>
 * Format is a single a-z character.
 *
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class ClusterId
{
  protected static final Pattern CLUSTER_ID_PATTERN = Pattern.compile("[a-z]");

  private final String id;

  public static ClusterId valueOf(@NonNull final String id)
  {
    if (!CLUSTER_ID_PATTERN.matcher(id).matches())
    {
      throw new IllegalArgumentException(id + " is not a valid region ID");
    }

    return new ClusterId(id);
  }
}

package com.jive.myco.jazz.core.coordinates;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Builder;

/**
 * Represents the global location of a service down to the cluster. A locality is composed of a
 * {@link RegionId}, {@link DatacenterId}, and {@link ClusterId}. A locality may be represented as a
 * string in the format {@code <REGION_ID>-<DATACENTER_ID><CLUSTER_ID>} where {@code <REGION_ID>},
 * {@code <DATACENTER_ID>} and {@code <CLUSTER_ID>} are valid {@link RegionId}, {@link DatacenterId}
 * and {@link ClusterId} string representations, respectively.
 *
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 *
 * @see RegionId
 * @see DatacenterId
 * @see ClusterId
 */
@Value
@Builder
public final class Locality
{
  protected static final Pattern LOCALITY_PATTERN = Pattern.compile(
      "(?<region>" + RegionId.REGION_ID_PATTERN.pattern() + ")" +
          "-(?<datacenter>" + DatacenterId.DATACENTER_ID_PATTERN.pattern() + ")" +
          "(?<cluster>" + ClusterId.CLUSTER_ID_PATTERN.pattern() + ")");

  @NonNull
  private RegionId region;

  @NonNull
  private DatacenterId datacenter;

  @NonNull
  private ClusterId cluster;

  /**
   * Returns the string representation of the locality.
   *
   * @see #valueOf(String)
   */
  @Override
  public String toString()
  {
    return String.format("%s-%s%s", region.getId(), datacenter.getId(), cluster.getId());
  }

  /**
   * Returns the locality parsed from the provided string.
   *
   * @param locality
   *          the string format for the locality
   *
   * @throws IllegalArgumentException
   *           if {@code locality} does not match the required format
   *
   * @see #toString()
   */
  public static Locality valueOf(@NonNull final String locality)
  {
    final Matcher matcher = LOCALITY_PATTERN.matcher(locality);
    if (!matcher.matches())
    {
      throw new IllegalArgumentException(locality + " is not a valid locality format");
    }

    return new Locality(RegionId.valueOf(matcher.group("region")),
        DatacenterId.valueOf(matcher.group("datacenter")),
        ClusterId.valueOf(matcher.group("cluster")));
  }

  /**
   * Returns true if this locality and {@code other} represent a location in the same region, data
   * center, and cluster.
   *
   * @param other
   *          the other locality to compare
   */
  public boolean sameCluster(final Locality other)
  {
    return equals(other);
  }

  /**
   * Returns true if this locality and {@code other} represent a location in the same region and
   * data center.
   *
   * @param other
   *          the other locality to compare
   */
  public boolean sameDatacenter(final Locality other)
  {
    return region.equals(other.region) && datacenter.equals(other.datacenter);
  }

  /**
   * Returns true if this locality and {@code other} represent a location in the same region.
   *
   * @param other
   *          the other locality to compare
   */
  public boolean sameRegion(final Locality other)
  {
    return region.equals(other.region);
  }
}

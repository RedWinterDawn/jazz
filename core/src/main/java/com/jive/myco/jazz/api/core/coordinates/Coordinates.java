package com.jive.myco.jazz.api.core.coordinates;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Builder;

/**
 * Represents the full path to an instance running in the Jazz ecosystem. An instance's coordinates
 * are comprised of the instance's {@link ProviderId provider}, {@link Locality location }, and
 * {@link InstanceId ID}. The coordinates of an instance represent the most fine grained addressing
 * information for an instance.
 * <p>
 * Coordinates may be represented as a string in the format
 * {@code <PROVIDER_ID>:<LOCALITY>:<INSTANCE_TYPE_ID>:<INSTANCE_ID>} where {@code <PROVIDER_ID>},
 * {@code <LOCALITY>}, {@code <INSTANCE_TYPE_ID>}, and {@code <INSTANCE_ID>} are valid
 * {@link ProviderId}, {@link Locality}, {@link InstanceTypeId}, and {@link InstanceId} string
 * representations, respectively.
 *
 * @author David Valeri
 *
 * @see ProviderId
 * @see Locality
 * @see InstanceTypeId
 * @see InstanceId
 */
@Value
@Builder
public final class Coordinates
{
  private static Pattern COORDINATES_PATTERN = Pattern.compile(
      "(?<provider>" + ProviderId.PROVIDER_ID_PATTERN.pattern() + "):"
          + "(?<locality>" + Locality.LOCALITY_PATTERN.pattern() + "):"
          + "(?<instanceType>" + InstanceTypeId.INSTANCE_TYPE_ID_PATTERN + "):"
          + "(?<instance>" + InstanceId.INSTANCE_ID_PATTERN.pattern() + ")");

  @NonNull
  private final ProviderId provider;

  @NonNull
  private final Locality locality;

  @NonNull
  private final InstanceTypeId instanceType;

  @NonNull
  private final InstanceId instance;

  /**
   * Returns the string representation of the coordinates.
   *
   * @see #valueOf(String)
   */
  @Override
  public String toString()
  {
    return String.format("%s:%s:%s:%s", provider, locality, instanceType, instance);
  }

  /**
   * Returns the coordinates parsed from the provided string.
   *
   * @param value
   *          the string format for the coordinates
   *
   * @throws IllegalArgumentException
   *           if {@code value} does not match the required format
   *
   * @see #toString()
   */
  public static Coordinates valueOf(final String value)
  {
    final Matcher matcher = COORDINATES_PATTERN.matcher(value);
    if (!matcher.matches())
    {
      throw new IllegalArgumentException(value + " is not a valid coordinates format");
    }

    return new Coordinates(
        ProviderId.valueOf(matcher.group("provider")),
        Locality.valueOf(matcher.group("locality")),
        InstanceTypeId.valueOf(matcher.group("instanceType")),
        InstanceId.valueOf(matcher.group("instance")));
  }

  public static final class CoordinatesBuilder
  {
    public CoordinatesBuilder instanceType(final InstanceTypeId instanceType)
    {
      this.instanceType = instanceType;
      return this;
    }

    @Deprecated
    public CoordinatesBuilder instanceType(
        final com.jive.myco.jazz.api.core.InstanceTypeId instanceType)
    {
      instanceType(InstanceTypeId.valueOf(instanceType.toString()));
      return this;
    }
  }
}

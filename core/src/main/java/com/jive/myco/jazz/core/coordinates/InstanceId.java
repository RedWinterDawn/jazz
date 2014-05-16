package com.jive.myco.jazz.core.coordinates;

import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

/**
 * Represents the unique identifier for an instance within the Jazz ecosystem.
 * <p>
 * An instance ID may be represented as a string in the following format {@code <INSTANCE_ID>} where
 * {@code <INSTANCE_ID>} is any combination of one or more lower case (a-z) letter, upper case
 * letter (A-Z), or digit (0-9).
 *
 * @author David Valeri
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class InstanceId
{
  protected static Pattern INSTANCE_ID_PATTERN = Pattern.compile("[a-zA-Z0-9]+");

  @NonNull
  private final String id;

  /**
   * Returns the string representation of the instance ID.
   *
   * @see #valueOf(String)
   */
  @Override
  public String toString()
  {
    return id;
  }

  /**
   * Returns the instance ID parsed from the provided string.
   *
   * @param value
   *          the string format for the instance ID
   *
   * @throws IllegalArgumentException
   *           if {@code value} does not match the required format
   *
   * @see #toString()
   */
  public static InstanceId valueOf(final String value)
  {
    if (!INSTANCE_ID_PATTERN.matcher(value).matches())
    {
      throw new IllegalArgumentException(value + " is not a valid instance ID");
    }

    return new InstanceId(value);
  }
}

package com.jive.myco.jazz.api.core.coordinates;

import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

/**
 * Represents the type identifier for the instance within the Jazz ecosystem.
 * <p>
 * An instance type ID may be represented as a string in the following format
 * {@code <INSTANCE_TYPE_ID>} where {@code <INSTANCE_TYPE_ID>} is one or more non-whitespace or
 * colon ":" characters.
 *
 * @author David Valeri
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public final class InstanceTypeId
{
  protected static Pattern INSTANCE_TYPE_ID_PATTERN = Pattern.compile("[^\\s:]+");

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
   * Returns the instance type ID parsed from the provided string.
   *
   * @param value
   *          the string format for the instance type ID
   *
   * @throws IllegalArgumentException
   *           if {@code value} does not match the required format
   *
   * @see #toString()
   */
  public static InstanceTypeId valueOf(final String value)
  {
    if (!INSTANCE_TYPE_ID_PATTERN.matcher(value).matches())
    {
      throw new IllegalArgumentException(value + " is not a valid instance type ID");
    }

    return new InstanceTypeId(value);
  }
}

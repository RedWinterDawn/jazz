package com.jive.myco.jazz.api.core.coordinates;

import java.util.regex.Pattern;

/**
 * Represents the type identifier for the instance within the Jazz ecosystem.
 * <p>
 * An instance type ID may be represented as a string in the following format
 * {@code <INSTANCE_TYPE_ID>} where {@code <INSTANCE_TYPE_ID>} is one or more non-whitespace or
 * colon ":" characters.
 *
 * @author David Valeri
 */
@SuppressWarnings("deprecation")
public final class InstanceTypeId extends com.jive.myco.jazz.api.core.InstanceTypeId
{
  protected static Pattern INSTANCE_TYPE_ID_PATTERN = Pattern.compile("[^\\s:]+");

  private InstanceTypeId(final String id)
  {
    super(id);
  }

  @Override
  public String getId()
  {
    return super.getId();
  }

  @Override
  public String toString()
  {
    return super.toString();
  }

  @Override
  public boolean equals(final Object obj)
  {
    return super.equals(obj);
  }

  @Override
  public int hashCode()
  {
    return super.hashCode();
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

package com.jive.myco.jazz.core.coordinates;

import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

/**
 * Represents the unique identifier for the provider hosting the instance within the Jazz ecosystem.
 * <p>
 * A provider ID may be represented as a string in the following format {@code <PROVIDER_ID>} where
 * {@code <PROVIDER_ID>} is any combination of one or more lower case (a-z) letter, upper case
 * letter (A-Z), or digit (0-9).
 *
 * @author David Valeri
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProviderId
{
  protected static Pattern PROVIDER_ID_PATTERN = Pattern.compile("[a-zA-Z0-9]+");

  @NonNull
  private final String id;

  /**
   * Returns the string representation of the provider ID.
   *
   * @see #valueOf(String)
   */
  @Override
  public String toString()
  {
    return id;
  }

  /**
   * Returns the provider ID parsed from the provided string.
   *
   * @param value
   *          the string format for the provider ID
   *
   * @throws IllegalArgumentException
   *           if {@code value} does not match the required format
   *
   * @see #toString()
   */
  public static ProviderId valueOf(final String value)
  {
    if (!PROVIDER_ID_PATTERN.matcher(value).matches())
    {
      throw new IllegalArgumentException(value + " is not a valid provider ID");
    }

    return new ProviderId(value);
  }
}

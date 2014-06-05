package com.jive.myco.jazz.api.core.network;

import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

/**
 * Represents the identifier for a network within the Jazz ecosystem.
 * <p>
 * An network name may be represented as a string in the following format {@code <NETWORK_ID>} where
 * {@code <NETWORK_ID>} is one or more non-whitespace characters.
 *
 * @author David Valeri
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NetworkId
{
  protected static Pattern NETWORK_ID_PATTERN = Pattern.compile("[^\\s]+");

  @NonNull
  private final String id;

  /**
   * Returns the string representation of the network ID.
   *
   * @see #valueOf(String)
   */
  @Override
  public String toString()
  {
    return id;
  }

  /**
   * Returns the network ID parsed from the provided string.
   *
   * @param value
   *          the string format for the network ID
   *
   * @throws IllegalArgumentException
   *           if {@code value} does not match the required format
   *
   * @see #toString()
   */
  public static NetworkId valueOf(final String value)
  {
    if (!NETWORK_ID_PATTERN.matcher(value).matches())
    {
      throw new IllegalArgumentException(value + " is not a valid network ID");
    }

    return new NetworkId(value);
  }
}

package com.jive.myco.jazz.api.core;

import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

/**
 * Represents the identifier for the environment in which the instance is running.
 * <p>
 * An environment ID may be represented as a string in the following format {@code <ENVIRONMENT_ID>}
 * where {@code <ENVIRONMENT_ID>} is one or more non-whitespace characters.
 *
 * @author David Valeri
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EnvironmentId
{
  private static final String PROD_ENV_ID = "prod";

  private static final String STAGING_ENV_ID = "staging";

  protected static final Pattern INSTANCE_TYPE_ID_PATTERN = Pattern.compile("[^\\s]+");

  public static final EnvironmentId PRODUCTION = EnvironmentId.valueOf(PROD_ENV_ID);

  public static final EnvironmentId STAGING = EnvironmentId.valueOf(STAGING_ENV_ID);

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
  public static EnvironmentId valueOf(final String value)
  {
    if (PROD_ENV_ID.equals(value))
    {
      return PRODUCTION;
    }
    else if (STAGING_ENV_ID.equals(value))
    {
      return PRODUCTION;
    }
    else if (!INSTANCE_TYPE_ID_PATTERN.matcher(value).matches())
    {
      throw new IllegalArgumentException(value + " is not a valid environment ID");
    }
    else
    {
      return new EnvironmentId(value);
    }
  }
}

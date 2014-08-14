package com.jive.myco.jazz.api.rules.czar.model;

import java.util.List;
import java.util.regex.Pattern;

import lombok.Value;

import com.jive.myco.jazz.api.core.coordinates.Coordinates;

@Value
public class RuleSet
{
  private final Pattern ruleCoordinatesPattern;
  private final List<SimpleRule> rules;
  private final int version;

  public boolean checkCoordinates(final Coordinates coordinates)
  {
    return ruleCoordinatesPattern.matcher(coordinates.toString()).matches();
  }
}

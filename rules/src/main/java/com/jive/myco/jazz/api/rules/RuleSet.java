package com.jive.myco.jazz.api.rules;

import java.util.List;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Builder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.jive.myco.jazz.api.core.coordinates.Coordinates;

@Value
@AllArgsConstructor
public class RuleSet
{
  private Pattern ruleCoordinatesPattern;
  private List<SimpleRule> rules;
  private int version;

  public boolean checkCoordinates(Coordinates coordinates)
  {
    return ruleCoordinatesPattern.matcher(coordinates.toString()).matches();
  }
}

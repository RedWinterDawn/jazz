package com.jive.myco.jazz.api.rules.czar.model;

import java.util.List;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.jive.myco.jazz.api.core.coordinates.Coordinates;

@AllArgsConstructor
@ToString
public class RuleSet
{
  @Getter
  @Setter
  private Pattern ruleCoordinatesPattern;
  @Getter
  private final List<SimpleRule> rules;
  @Getter
  @Setter
  private int version;

  public boolean checkCoordinates(final Coordinates coordinates)
  {
    return ruleCoordinatesPattern.matcher(coordinates.toString()).matches();
  }
}

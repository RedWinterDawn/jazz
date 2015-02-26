package com.jive.myco.jazz.api.rules.czar.model;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import com.jive.myco.jazz.api.core.coordinates.Coordinates;

@ToString
public class RuleSet
{
  @Getter
  private final Pattern ruleCoordinatesPattern;

  @Getter
  private final List<SimpleRule> rules;

  @Getter
  private final int version;

  @ConstructorProperties({ "ruleCoordinatesPattern", "rules", "version" })
  public RuleSet(
      @NonNull final Pattern ruleCoordinatesPattern,
      @NonNull final List<SimpleRule> rules,
      final int version)
  {
    this.ruleCoordinatesPattern = ruleCoordinatesPattern;
    this.rules = Collections.unmodifiableList(new ArrayList<>(rules));
    this.version = version;
  }

  public boolean checkCoordinates(final Coordinates coordinates)
  {
    return ruleCoordinatesPattern.matcher(coordinates.toString()).matches();
  }
}

package com.jive.myco.jazz.api.rules;

import java.util.List;

import lombok.Value;

@Value()
public class RuleSet
{
  public static final String LOCATION_EBC = "EBC";
  public static final String LOCATION_ZUUL = "ZUUL";
  private final  String location;
  private final List<Rule> rules;
  private final int version;

  
}

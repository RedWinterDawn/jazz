package com.jive.myco.jazz.api.rules;

import java.util.List;
import java.util.regex.Pattern;

import com.jive.myco.jazz.api.core.coordinates.Coordinates;

import lombok.Data;
import lombok.Value;
import lombok.experimental.Builder;

@Data
public class RuleSet
{ 
  private Pattern ruleCoordinates; 
  private final List<SimpleRule> rules;
  private final int version;
  
  public boolean checkCoordinates(Coordinates coordinates){
    return ruleCoordinates.matcher(coordinates.toString()).matches();
  }
  
  public void setRuleCoordinates(String coordinatesPattern){
    if(Pattern.matches("^(\\S+):(\\S+):(\\S+):(\\S+)$", coordinatesPattern)){
      ruleCoordinates = Pattern.compile(coordinatesPattern);
    }
  }
}

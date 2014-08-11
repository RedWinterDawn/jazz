package com.jive.myco.jazz.api.rules;

import java.util.List;
import java.util.regex.Pattern;

import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * Represents a rule. 
 * @author jnorton && zmorin
 *
 */
@Value
@EqualsAndHashCode(of={"description"})
public class Rule
{
  public int getKey(){
    return this.hashCode();
  }
  
  private String description;
  private Pattern pattern;
  private List<Action> actions;
  private int priority;
  
}

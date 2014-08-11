package com.jive.myco.jazz.api.rules;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.Builder;

/**
 * Represents a rule. 
 * @author jnorton
 * @author zmorin
 *
 */
@Builder
@Value
@EqualsAndHashCode(of={"description"})
public class SimpleRule
{
  public int getKey(){
    return this.hashCode();
  }
  private String description;
  private RuleExpression expression;
  private Action action;
  private int priority;
}

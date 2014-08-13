package com.jive.myco.jazz.api.rules;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.Builder;

import com.jive.myco.jazz.api.context.JazzContextManager;

/**
 * Represents a rule.
 * 
 * @author jnorton
 * @author zmorin
 *
 */
@Builder
@Value
@EqualsAndHashCode(of = { "expression" })
@AllArgsConstructor
public class SimpleRule
{
  private final String description;
  private final RuleExpression expression;
  private final Action action;

  public void applyRule(JazzContextManager contextManager)
  {
    if (expression.eval(contextManager.getLocalContext().toMap()))
    {
      contextManager.getContext().put(action.getKey(), action.getValue());
    }
  }

  public int key()
  {
    return this.hashCode();
  }
}

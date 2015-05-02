package com.jive.myco.jazz.api.rules.czar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

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
@AllArgsConstructor
@Slf4j
public class SimpleRule
{
  private final String description;
  private final RuleExpression expression;
  private final Action action;

  public void apply()
  {
    log.debug("Checking Rule Expression [{}]", expression);
    if (expression.eval())
    {
      log.debug("Put the following in context [{}], [{}]", action.getKey(), action.getValue());
      JazzContextManager.put(action.getKey(), action.getValue());
    }
  }

  public int getRuleId()
  {
    return this.expression.hashCode();
  }
}

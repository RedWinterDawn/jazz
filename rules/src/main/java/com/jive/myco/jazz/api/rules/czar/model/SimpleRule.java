package com.jive.myco.jazz.api.rules.czar.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.Builder;
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
@EqualsAndHashCode(of = { "expression" })
@AllArgsConstructor
@Slf4j
public class SimpleRule
{
  private final String description;
  private final RuleExpression expression;
  private final Action action;

  public void applyRule(final JazzContextManager contextManager)
  {
    log.debug("Checking Rule Expression [{}]", expression);
    if (expression.eval(contextManager))
    {
      log.debug("Put the following in context [{}], [{}]", action.getKey(), action.getValue());
      contextManager.put(action.getKey(), action.getValue());
    }
  }

  public int getRuleId()
  {
    return this.hashCode();
  }
}

package com.jive.myco.jazz.api.rules.czar.model;


/**
 * Engine to evaluate and apply rule sets.
 *
 * @author jnorton
 */
public interface RulesEngine
{
  /**
   * Apply the current set of rules to the Jazz Context.
   */
  void applyRules();

  /**
   * Update the current set of rules used in {@link #applyRules()}.
   *
   * @param ruleSet
   *          the new rules to use
   */
  void updateRules(final RuleSet ruleSet);
}

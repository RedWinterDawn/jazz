package com.jive.myco.jazz.api.rules.czar.model;

import com.jive.myco.commons.lifecycle.ListenableLifecycled;

/**
 * Engine to evaluate and apply rule sets.
 *
 * @author jnorton
 */
public interface RulesEngine extends ListenableLifecycled
{
  /**
   * Apply the current set of rules to the Jazz Context.
   */
  void applyRules();
}

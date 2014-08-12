package com.jive.myco.jazz.api.rules;

import com.jive.myco.jazz.api.core.coordinates.Coordinates;

public interface RulesManager
{
    RuleSet getRuleSet(Coordinates coordinates);
    void addRule(RuleSet ruleSet, SimpleRule rule, SimpleRule addAfterRule);
    void removeRule(RuleSet ruleSet, SimpleRule rule);
}  

  // @Override
  // public RuleSet getRuleSet(String location) throws RuleException
  // {
  // try
  // {
  // return PostgresUtil.getRuleSet(location);
  // }
  // catch (Exception e)
  // {
  // Throwables.propagateIfInstanceOf(e, RuleException.class);
  // throw new RuleException(e);
  // }
  // }
  //
  // @Override
  // public void addRule(RuleSet ruleSet, SimpleRule rule, SimpleRule addAfterRule)
  // throws RuleException
  // {
  // int insertLocation = ruleSet.getRules().indexOf(addAfterRule) + 1;
  // ruleSet.getRules().add(insertLocation, rule);
  // saveRuleSet(ruleSet);
  // }
  //
  // @Override
  // public void removeRule(String location, SimpleRule rule) throws RuleException
  // {
  // ruleSet.getRules().remove(rule);
  // saveRuleSet(ruleSet);
  // }
  //
  // @Override
  // public List<Action> getActions(String testVal) throws RuleException
  // {
  // // List<Action> actions = Lists.newArrayList();
  // // ruleSet.getRules().stream().forEach((rule) ->
  // // {
  // // if (rule.getPattern().matcher(testVal).matches())
  // // {
  // // actions.addAll(rule.getActions());
  // // }
  // // });
  // // return actions;
  // return null;
  // }
  //
  // private void saveRuleSet(RuleSet ruleSet) throws RuleException
  // {
  // try
  // {
  // PostgresUtil.saveRuleSet(ruleSet);
  // }
  // catch (Exception e)
  // {
  // Throwables.propagateIfInstanceOf(e, RuleException.class);
  // throw new RuleException(e);
  // }
  //
  // this.ruleSet = ruleSet;
  // }

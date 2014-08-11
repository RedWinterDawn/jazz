package com.jive.myco.jazz.api.rules;

import java.util.List;

import com.jive.myco.jazz.api.rules.exceptions.RuleException;

public interface RuleService
{
  RuleSet getRuleSet(String location) throws RuleException;
  void addRule(RuleSet ruleSet, Rule rule, Rule addAfterRule) throws RuleException;
  void removeRule(String location, Rule rule) throws RuleException;
  List<Action> getActions(String testVal) throws RuleException;
}

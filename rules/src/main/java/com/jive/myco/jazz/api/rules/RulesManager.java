package com.jive.myco.jazz.api.rules;

import java.util.List;

import com.jive.myco.jazz.api.core.coordinates.Coordinates;
import com.jive.myco.jazz.api.rules.exceptions.RuleException;

public interface RulesManager
{
    RuleSet getRuleSet(Coordinates coordinates) throws Exception;
    List<RuleSet> getRuleSets() throws Exception;
    void addRule(RuleSet ruleSet, SimpleRule rule, SimpleRule addAfterRule) throws RuleException;
    void removeRule(RuleSet ruleSet, SimpleRule rule) throws RuleException;
}  
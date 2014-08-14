package com.jive.myco.jazz.api.rules.czar.model;

import static org.junit.Assert.*;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.jive.myco.jazz.api.context.JazzContextManager;

@RunWith(MockitoJUnitRunner.class)
public class RuleSetTest
{

  @Before
  public void setup()
  {
    JazzContextManager.clear();
  }

  @After
  public void tearDown()
  {
    JazzContextManager.clear();
  }

  @Test
  public void testRuleSetSuccess() throws Exception
  {
    JazzContextManager.put("phonenumber", "8015551212");
    JazzContextManager.put("areacode", "801");
    JazzContextManager.put("state", "");

    final List<SimpleRule> rules = Lists.newArrayList();
    rules.add(SimpleRule.builder()
        .action(Action.valueOf("setCtxProperty(Hello, Equals)"))
        .expression(RuleExpression.valueOf("phonenumber equals 8015551212"))
        .description("Test Rule")
        .build());
    rules.add(SimpleRule.builder()
        .action(Action.valueOf("setCtxProperty(Hello, Matches)"))
        .expression(RuleExpression.valueOf("areacode matches ^80.*$"))
        .description("Test Rule 2")
        .build());

    rules.add(SimpleRule.builder()
        .action(Action.valueOf("setCtxProperty(Hello, IsEmpty)"))
        .expression(RuleExpression.valueOf("state isempty"))
        .description("Test Rule 3")
        .build());

    final RuleSet ruleSet = new RuleSet(Pattern.compile(".*"), rules, 1);

    ruleSet.getRules().stream().forEach(SimpleRule::apply);

    assertEquals("IsEmpty", JazzContextManager.get("Hello"));
  }
}

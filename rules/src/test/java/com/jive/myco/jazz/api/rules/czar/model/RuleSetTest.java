package com.jive.myco.jazz.api.rules.czar.model;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.jive.myco.jazz.api.context.JazzContextManager;

@RunWith(MockitoJUnitRunner.class)
public class RuleSetTest
{

  @Mock
  JazzContextManager mockJazzContextManager;

  @Test
  public void testRuleSetSuccess() throws Exception
  {

    when(mockJazzContextManager.get("phonenumber", "")).thenReturn("8015551212");
    when(mockJazzContextManager.get("areacode", "")).thenReturn("801");
    when(mockJazzContextManager.get("state", "")).thenReturn("");

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

    ruleSet.getRules().stream().forEach((rule) ->
    {
      rule.applyRule(mockJazzContextManager);
    });

    verify(mockJazzContextManager, times(3)).get(anyString(), eq(""));
    verify(mockJazzContextManager, times(3)).put(eq("Hello"), anyString());
  }
}

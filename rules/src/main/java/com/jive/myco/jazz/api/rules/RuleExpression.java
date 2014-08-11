package com.jive.myco.jazz.api.rules;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.jive.myco.jazz.api.rules.exceptions.RuleException;

import lombok.NonNull;

public class RuleExpression
{

  private final static String OPERATOR_EQUALS = "equals";
  private final static String OPERATOR_MATCHES = "matches";

  private final Pattern pattern = Pattern.compile("^([A-z.]+)\\s(equals|matches)\\s([A-z.]+)$");
  private String expression;
  private String leftSide;
  private String operator;
  private String rightSide;

  public boolean eval(Map<String, String> variables)
  {
    String valueToCompare = variables.get(leftSide);

    if (operator.equals(OPERATOR_EQUALS))
    {
      return valueToCompare.equals(rightSide);
    }
    else if (operator.equals(OPERATOR_MATCHES))
    {
      return Pattern.matches(rightSide, valueToCompare);
    }
    else
    {
      return false;
    }
  }

  @JsonCreator
  public void valueOf(@NonNull String expression) throws RuleException
  {
    Matcher matcher = pattern.matcher(expression);
    if (matcher.matches())
    {
      this.expression = expression;
      this.leftSide = matcher.group(0);
      this.operator = matcher.group(1);
      this.rightSide = matcher.group(2);
    }
    throw new RuleException(String.format("Expression [%s] does not match pattern [%s]",
        expression, pattern.pattern()));
  }

  public String toString()
  {
    return expression;
  }

}

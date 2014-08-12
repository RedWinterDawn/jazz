package com.jive.myco.jazz.api.rules;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Builder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.jive.myco.jazz.api.rules.exceptions.RuleException;

@Builder
@Value
public class RuleExpression
{

  private final static String OPERATOR_EQUALS = "equals";
  private final static String OPERATOR_MATCHES = "matches";

  private final static Pattern pattern = Pattern
      .compile("^([A-z.]+)\\s(equals|matches)\\s([A-z.]+)$");
  private String expression;
  private String leftSide;
  private String operator;
  private String rightSide;
  
  protected boolean eval(Map<String, String> variables)
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
  public static RuleExpression valueOf(@NonNull String expression) throws RuleException
  {
   
    Matcher matcher = pattern.matcher(expression);
    if (matcher.matches())
    {
      return RuleExpression.builder()
        .expression(expression)
        .leftSide(matcher.group(1))
        .operator(matcher.group(2))
        .rightSide(matcher.group(3)).build();
    }
    else
    {
      throw new RuleException(String.format("Expression [%s] does not match pattern [%s]",
          expression, pattern.pattern()));
    }
  }

  @JsonValue
  public String toString()
  {
    return expression;
  }

}

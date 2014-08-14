package com.jive.myco.jazz.api.rules.czar.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.NonNull;
import lombok.Value;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.jive.myco.jazz.api.context.JazzContextManager;
import com.jive.myco.jazz.api.rules.czar.exceptions.RuleException;

@Value
public class RuleExpression
{
  public final static String OPERATOR_EQUALS = "equals";
  public final static String OPERATOR_MATCHES = "matches";
  public final static String OPERATOR_IS_EMPTY = "isempty";

  private final static Pattern EXPRESSION_PATTERN = Pattern
      .compile("^([A-z0-9.]+)\\s(equals|matches)\\s(.+)$|^([A-z0-9.]+)\\s(isempty)$",
          Pattern.CASE_INSENSITIVE);

  private final String expression;
  private final String leftSide;
  private final String operator;
  private final Pattern rightSide;

  private RuleExpression(final String expression, final String leftSide, final String operator,
      final String rightSide)
  {
    this.expression = expression;
    this.leftSide = leftSide;
    this.operator = operator;
    if (OPERATOR_EQUALS.equalsIgnoreCase(operator))
    {
      this.rightSide = Pattern.compile(rightSide, Pattern.LITERAL);
    }
    else if (OPERATOR_MATCHES.equalsIgnoreCase(operator))
    {
      this.rightSide = Pattern.compile(rightSide);
    }
    else if (OPERATOR_IS_EMPTY.equalsIgnoreCase(operator))
    {
      this.rightSide = Pattern.compile("");
    }
    else
    {
      throw new IllegalArgumentException(String.format("Invalid operator [%s]", operator));
    }
  }

  protected boolean eval()
  {
    final String valueToCompare = JazzContextManager.get(leftSide, "");

    return rightSide.matcher(valueToCompare).matches();
  }

  @JsonCreator
  public static RuleExpression valueOf(@NonNull final String expression) throws RuleException
  {
    final Matcher matcher = EXPRESSION_PATTERN.matcher(expression);

    if (matcher.matches() && null != matcher.group(1))
    {
      return new RuleExpression(expression, matcher.group(1), matcher.group(2), matcher.group(3));
    }
    else if (matcher.matches() && null != matcher.group(4))
    {
      return new RuleExpression(expression, matcher.group(4), matcher.group(5), "");
    }
    else
    {
      throw new RuleException(String.format("Expression [%s] does not match pattern [%s]",
          expression, EXPRESSION_PATTERN.pattern()));
    }
  }

  @Override
  @JsonValue
  public String toString()
  {
    return expression;
  }

}

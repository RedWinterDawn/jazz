package com.jive.myco.jazz.api.rules.czar.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.jive.myco.jazz.api.rules.czar.exceptions.RuleException;


/**
 *
 * @author jnorton && zmorin
 *
 */
@Value
@Builder
public class Action
{
  public final static String METHOD_SET_CONTEXT = "setCtxProperty";
  private final static Pattern PATTERN = Pattern
      .compile("^(setCtxProperty)\\((.+),\\s(.+)\\)$");

  private String expression;
  private String method;
  private String key;
  private String value;

  @JsonCreator
  public static Action valueOf(@NonNull final String expression) throws RuleException
  {
    final Matcher matcher = PATTERN.matcher(expression);
    if (matcher.matches())
    {
      return Action.builder()
          .expression(expression)
          .method(matcher.group(1))
          .key(matcher.group(2))
          .value(matcher.group(3))
          .build();
    }

    throw new RuleException(String.format("Action Expression [%s] does not match pattern [%s]",
        expression, PATTERN.pattern()));
  }

  @Override
  @JsonValue
  public String toString()
  {
    return expression;
  }
}

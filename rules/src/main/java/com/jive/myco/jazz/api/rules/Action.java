package com.jive.myco.jazz.api.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Data;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Builder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.jive.myco.jazz.api.rules.exceptions.RuleException;

/**
 * 
 * @author jnorton && zmorin
 *
 */

@Builder
@Value
public class Action
{
  public final static String METHOD_SET_CONTEXT = "setCtxProperty";
  private final static Pattern PATTERN = Pattern
      .compile("^(setCtxProperty)\\((\"[A-z.]+\"),(\"[A-z.]+\")\\)$");

  private String expression;
  private String method;
  private String key;
  private String value;

  @JsonCreator
  public static Action valueOf(@NonNull String expression) throws RuleException
  {
    Matcher matcher = PATTERN.matcher(expression);
    if (matcher.matches())
    {
      return Action.builder()
          .expression(expression)
          .method(matcher.group(0))
          .key(matcher.group(1))
          .value(matcher.group(2))
          .build();
    }
    throw new RuleException(String.format("Action Expression [%s] does not match pattern [%s]",
        expression, PATTERN.pattern()));
  }
}

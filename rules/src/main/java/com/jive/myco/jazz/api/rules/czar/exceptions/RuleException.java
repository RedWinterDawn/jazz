package com.jive.myco.jazz.api.rules.czar.exceptions;


/**
 * Basic catch all exception thrown around inside a RuleService
 *
 * @author jnorton && zmorin
 */
public class RuleException extends Exception
{
  private static final long serialVersionUID = -4627547538994584341L;

  public RuleException(final String message){
    super(message);
  }

  public RuleException(final Throwable e)
  {
    super(e);
  }

  public RuleException(final String message, final Throwable cause)
  {
    super(message, cause);
  }
}

package com.jive.myco.jazz.api.rules.exceptions;


/**
 * Basic catch all exception thrown around inside a RuleService
 * @author jnorton && zmorin
 *
 */
public class RuleException extends Exception
{
  private static final long serialVersionUID = -4627547538994584341L;

  public RuleException(String message){
    super(message);
  }
  
  public RuleException(Exception e)
  {
    super(e);
  }
}

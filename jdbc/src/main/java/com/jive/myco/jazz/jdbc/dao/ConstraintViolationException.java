package com.jive.myco.jazz.jdbc.dao;

/**
 * An exception that represents an error on an operation that would create a violation of a
 * constraint on the data in the data store.
 *
 * @author David Valeri
 * @deprecated use {@link com.jive.myco.jazz.api.jdbc.dao.ConstraintViolationException}
 */
@Deprecated
public class ConstraintViolationException extends DaoException
{
  private static final long serialVersionUID = 3892237930667695938L;

  public ConstraintViolationException()
  {
    super();
  }

  public ConstraintViolationException(final String message, final Throwable cause)
  {
    super(message, cause);
  }

  public ConstraintViolationException(final String message)
  {
    super(message);
  }

  public ConstraintViolationException(final Throwable cause)
  {
    super(cause);
  }
}

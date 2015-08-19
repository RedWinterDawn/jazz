package com.jive.myco.jazz.jdbc.dao;

/**
 * An exception that represents an error on an operation that would introduce a duplicate key into
 * the data store.
 *
 * @author David Valeri
 * @deprecated  Use {@link com.jive.myco.jazz.api.jdbc.dao.DuplicateKeyException}
 */
@Deprecated
public class DuplicateKeyException extends DaoException
{
  private static final long serialVersionUID = 3892237930667695938L;

  public DuplicateKeyException()
  {
    super();
  }

  public DuplicateKeyException(final String message, final Throwable cause)
  {
    super(message, cause);
  }

  public DuplicateKeyException(final String message)
  {
    super(message);
  }

  public DuplicateKeyException(final Throwable cause)
  {
    super(cause);
  }
}

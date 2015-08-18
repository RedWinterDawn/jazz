package com.jive.myco.jazz.jdbc.dao;

/**
 * Base class for the DAO exception hierarchy.
 *
 * @author David Valeri
 * @deprecated Use {@link com.jive.myco.jazz.api.jdbc.dao.DaoException}
 */
@Deprecated
public class DaoException extends com.jive.myco.jazz.api.jdbc.dao.DaoException
{
  private static final long serialVersionUID = -7398989543752230591L;

  public DaoException()
  {
    super();
  }

  protected DaoException(final String message, final Throwable cause,
      final boolean enableSuppression,
      final boolean writableStackTrace)
  {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public DaoException(final String message, final Throwable cause)
  {
    super(message, cause);
  }

  public DaoException(final String message)
  {
    super(message);
  }

  public DaoException(final Throwable cause)
  {
    super(cause);
  }
}

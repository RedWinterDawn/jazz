package com.jive.myco.jazz.api.jdbc.dao;

import java.sql.SQLException;

/**
 * Interface for translators that can map a generic {@link SQLException} to a {@link DaoException}.
 *
 * @author David Valeri
 */
public interface SqlExceptionTranslator
{
  /**
   * Walks the exception hierarchy towards the root cause, stopping when a {@link SQLException} is
   * found and translating the exception into a new {@link DaoException} with {@code t} as the
   * cause. Returns a generic {@link DaoException} with {@code t} as the cause if no
   * {@code SQLException} is found in the hierarchy or if no specific subclass of
   * {@code DaoException} maps to the error represented by the identified {@code SQLException}.
   *
   * @param t
   *          the throwable to inspect
   */
  DaoException translate(final Throwable t);
}

package com.jive.myco.jazz.jdbc;

import javax.sql.DataSource;

import com.jive.myco.commons.lifecycle.ListenableLifecycled;

/**
 * The interface for data sources returned from the managers in the Jazz JDBC API.
 *
 * @author David Valeri
 * @deprecated use {@link com.jive.myco.jazz.api.jdbc.PooledDataSource} instead
 */
@Deprecated
public interface PooledDataSource extends DataSource, ListenableLifecycled,
    com.jive.myco.jazz.api.jdbc.PooledDataSource
{
  // No-op
}

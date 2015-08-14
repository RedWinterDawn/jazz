package com.jive.myco.jazz.api.jdbc;

import javax.sql.DataSource;

import com.jive.myco.commons.lifecycle.ListenableLifecycled;

/**
 * The interface for data sources returned from the managers in the Jazz JDBC API.
 *
 * @author David Valeri
 */
public interface PooledDataSource extends DataSource, ListenableLifecycled
{
  // No-op
}

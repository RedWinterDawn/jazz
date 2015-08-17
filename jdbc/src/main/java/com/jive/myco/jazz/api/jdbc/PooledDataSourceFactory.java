package com.jive.myco.jazz.api.jdbc;


/**
 * The factory API for creating pooled data source instances.
 *
 * @author David Valeri
 */
public interface PooledDataSourceFactory
{
  /**
   * Creates a new {@code PooledDataSource} instance based on the provided descriptor.
   *
   * @param pooledDataSourceDescriptor
   *          the descriptor used to configure the pool
   *
   * @return a new instance
   */
  PooledDataSource createPooledDataSource(
      final PooledDataSourceDescriptor pooledDataSourceDescriptor);
}

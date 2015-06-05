package com.jive.myco.jazz.jdbc;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import com.google.common.base.Preconditions;

/**
 * A descriptor for creating pooled data sources.
 *
 * @author David Valeri
 */
@Getter
@ToString
public class PooledDataSourceDescriptor
{
  @NonNull
  private final String id;

  private final String jdbcUrl;
  private final DataSource dataSource;
  private final String user;
  private final String password;

  /**
   * The time, in ms, after which a connection attempt is abandoned and a timeout exception is
   * returned to the caller. Defaults to {@code 30} seconds. A value less than 1 indicates that an
   * effectively infinite timeout should be used.
   */
  private final long connectionTimeout;

  /**
   * The time, in ms, after which idle connections are expunged from the pool. Defaults to
   * {@code 10} minutes. A value less than 1 indicates an effectively infinite idle timeout.
   */
  private final long idleTimeout;

  /**
   * The time, in ms, after which connections are expunged from the pool. Defaults to {@code 30}
   * minutes. A value less than 1 indicates an effectively infinite lifetime.
   */
  private final long maxLifetime;

  /**
   * The maximum pool size. Defaults to {@code 10}.
   */
  private final int maxPoolSize;

  /**
   * The minimum number of connections to keep idle in the pool. Defaults to {@code 1}.
   */
  private final int minIdle;

  /**
   * The optional properties to provide to the data source class when managing the data source
   * internally.
   */
  private final Properties dataSourceProperties;

  /**
   * Indicates if the created pool should attempt to register health checks if the health check
   * system is available. Defaults to {@code true}.
   */
  private final boolean registerHealthChecks;

  /**
   * The period, in ms, at which the connectivity health check attempts to retrieve a connection
   * from the pool. Defaults to {@code 30} seconds.
   */
  private final long connectivityHealthCheckPeriod;

  @Builder
  public PooledDataSourceDescriptor(
      final String id,
      final String jdbcUrl,
      final DataSource dataSource,
      final String user,
      final String password,
      final long connectionTimeout,
      final long idleTimeout,
      final long maxLifetime,
      final int maxPoolSize,
      final int minIdle,
      final Properties dataSourceProperties,
      final boolean registerHealthChecks,
      final long connectivityHealthCheckPeriod)
  {
    this.id = id;
    this.jdbcUrl = jdbcUrl;
    this.dataSource = dataSource;
    this.user = user;
    this.password = password;
    this.connectionTimeout = connectionTimeout;
    this.idleTimeout = idleTimeout;
    this.maxLifetime = maxLifetime;
    this.maxPoolSize = maxPoolSize;
    this.minIdle = minIdle;

    if (dataSourceProperties != null)
    {
      this.dataSourceProperties = new Properties();

      dataSourceProperties.stringPropertyNames()
          .forEach((name) -> this.dataSourceProperties
              .setProperty(name, dataSourceProperties.getProperty(name)));
    }
    else
    {
      this.dataSourceProperties = null;
    }

    this.registerHealthChecks = registerHealthChecks;
    this.connectivityHealthCheckPeriod = connectivityHealthCheckPeriod;

    Preconditions.checkArgument(connectivityHealthCheckPeriod > 0,
        "connectivityHealthCheckPeriod must be greater than 0.");
  }

  public Properties getDataSourceProperties()
  {
    return new Properties(this.dataSourceProperties);
  }

  public static final class PooledDataSourceDescriptorBuilder
  {
    private long connectionTimeout = TimeUnit.SECONDS.toMillis(30);
    private long idleTimeout = TimeUnit.MINUTES.toMillis(10);
    private long maxLifetime = TimeUnit.MINUTES.toMillis(30);
    private int maxPoolSize = 10;
    private int minIdle = 1;

    private boolean registerHealthChecks = true;

    private long connectivityHealthCheckPeriod = TimeUnit.SECONDS.toMillis(30);
  }
}

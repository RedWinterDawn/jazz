package com.jive.myco.jazz.api.health;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Builder;

/**
 * A descriptor for the creation of an {@link AggregateHealthCheck} via the
 * {@link HealthCheckManager}. New instances of the descriptor may be obtained from the
 * {@link AggregateHealthCheckDescriptor#builder() builder}.
 *
 * @author David Valeri
 */
@Getter
public class AggregateHealthCheckDescriptor
{
  /**
   * The unique ID of the resulting aggregate health check.
   */
  private final String id;

  /**
   * The health checks used to compose the resulting aggregate health check. May be empty.
   */
  private final Set<HealthCheck> healthChecks;

  @Builder
  private AggregateHealthCheckDescriptor(@NonNull final String id,
      @NonNull final Set<HealthCheck> healthChecks)
  {
    this.id = id;
    this.healthChecks = new HashSet<>(healthChecks);
  }

  public static final class AggregateHealthCheckDescriptorBuilder
  {
    private final Set<HealthCheck> healthChecks = new HashSet<>();

    /**
     * Adds a health check to the checks provided via the descriptor.
     * <p>
     * If a health check that {@link Object#equals(Object) equals} {@code healthCheck} has already
     * been configured, this call overwrites the previous value.
     *
     * @param healthCheck
     *          the health check to add
     *
     * @return this builder for chaining
     */
    public AggregateHealthCheckDescriptorBuilder addHealthCheck(final HealthCheck healthCheck)
    {
      healthChecks.add(healthCheck);
      return this;
    }

    /**
     * Adds health checks to the health checks provided via the descriptor.
     * <p>
     * If a health check that {@link Object#equals(Object) equals} a value provided by
     * {@code healthChecks} has already been configured, this call overwrites the previous value.
     *
     * @param additionalHealthChecks
     *          the health checks to add
     *
     * @return this builder for chaining
     */
    public AggregateHealthCheckDescriptorBuilder addHealthChecks(
        @NonNull final Iterable<? extends HealthCheck> additionalHealthChecks)
    {
      additionalHealthChecks.forEach(healthChecks::add);
      return this;
    }

    /**
     * Adds health checks to the health checks provided via the descriptor.
     * <p>
     * If a health check that {@link Object#equals(Object) equals} a value provided by
     * {@code healthChecks} has already been configured, this call overwrites the previous value.
     *
     * @param additionalHealthChecks
     *          the health checks to add
     *
     * @return this builder for chaining
     */
    public AggregateHealthCheckDescriptorBuilder addHealthChecks(
        @NonNull final HealthCheck... additionalHealthChecks)
    {
      Collections.addAll(healthChecks, additionalHealthChecks);
      return this;
    }

    @SuppressWarnings("unused")
    private void healthChecks()
    {
      // Hidden from Lombok
    }
  }
}

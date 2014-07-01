package com.jive.myco.jazz.api.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Builder;

import com.jive.myco.jazz.api.web.FilterMappingDescriptor;
import com.jive.myco.jazz.api.web.StaticResourceDescriptor;

/**
 * Represents the components that comprise a REST service. See the {@link #builder() builder} for
 * more information on constructing a valid descriptor.
 *
 * @author David Valeri
 */
@Getter
public final class RestServiceDescriptor
{
  private final Set<Object> singletons;

  private final List<FilterMappingDescriptor> filters;

  private final Set<StaticResourceDescriptor> staticResources;

  @Builder
  private RestServiceDescriptor(
      @NonNull final Set<Object> singletons,
      @NonNull final List<FilterMappingDescriptor> filters,
      @NonNull final List<StaticResourceDescriptor> staticResources)
  {
    this.singletons = Collections.unmodifiableSet(new HashSet<>(singletons));
    this.staticResources =
        Collections.unmodifiableSet(new HashSet<>(staticResources));
    this.filters =
        Collections.unmodifiableList(new ArrayList<>(filters));
  }

  /**
   * A builder for creating {@link RestServiceDescriptor} instances.
   *
   * @author David Valeri
   */
  public static final class RestServiceDescriptorBuilder
  {
    private final Set<Object> singletons = new HashSet<>();

    private final List<FilterMappingDescriptor> filters = new LinkedList<>();

    private final List<StaticResourceDescriptor> staticResources = new LinkedList<>();

    /**
     * Adds a singleton to the singletons provided via the descriptor.
     * <p>
     * If a singleton that {@link Object#equals(Object) equals} {@code singleton} has already been
     * configured, this call overwrites the previous value.
     * <p>
     * Singletons may be any JAX-RS related object including a REST resource or a {@code Provider}
     *
     * @param singleton
     *          the singleton JAX-RS resource to add
     *
     * @return this builder for chaining
     */
    public RestServiceDescriptorBuilder addSingleton(final Object singleton)
    {
      singletons.add(singleton);
      return this;
    }

    /**
     * Adds singletons to the singletons provided via the descriptor.
     * <p>
     * If a singleton that {@link Object#equals(Object) equals} a value provided by
     * {@code singletons} has already been configured, this call overwrites the previous value.
     *
     * @param singletons
     *          the singletons to add
     *
     * @return this builder for chaining
     */
    public RestServiceDescriptorBuilder addSingletons(
        @NonNull final Iterable<? extends Object> singletons)
    {
      singletons.forEach(this.singletons::add);
      return this;
    }

    /**
     * Adds singletons to the singletons provided via the descriptor.
     * <p>
     * If a singleton that {@link Object#equals(Object) equals} a value provided by
     * {@code singletons} has already been configured, this call overwrites the previous value.
     *
     * @param singletons
     *          the singletons to add
     *
     * @return this builder for chaining
     */
    public RestServiceDescriptorBuilder addSingletons(@NonNull final Object... singletons)
    {
      Collections.addAll(this.singletons, singletons);
      return this;
    }

    /**
     * Adds a filter to the filters provided via the descriptor.
     * <p>
     * Filters are provided in the order in which they are added to this builder.
     *
     * @param filterDescriptor
     *          the filter to add
     *
     * @return this builder for chaining
     */
    public RestServiceDescriptorBuilder addFilter(final FilterMappingDescriptor filterDescriptor)
    {
      filters.add(filterDescriptor);
      return this;
    }

    /**
     * Adds a filter to the filters provided via the descriptor.
     *
     * @param filterDescriptors
     *          the filters to add
     *
     * @return this builder for chaining
     */
    public RestServiceDescriptorBuilder addFilters(
        @NonNull final Iterable<? extends FilterMappingDescriptor> filterDescriptors)
    {
      filterDescriptors.forEach(this.filters::add);
      return this;
    }

    /**
     * Adds filters to the filters provided via the descriptor.
     *
     * @param filterDescriptors
     *          the filters to add
     *
     * @return this builder for chaining
     */
    public RestServiceDescriptorBuilder addFilters(
        @NonNull final FilterMappingDescriptor... filterDescriptors)
    {
      Collections.addAll(this.filters, filterDescriptors);
      return this;
    }

    /**
     * Adds a static resource to the static resources provided via the descriptor.
     *
     * @param staticResourceDescriptor
     *          the resource to add
     *
     * @return this builder for chaining
     */
    public RestServiceDescriptorBuilder addStaticResource(
        @NonNull final StaticResourceDescriptor staticResourceDescriptor)
    {
      staticResources.add(staticResourceDescriptor);
      return this;
    }

    /**
     * Adds static resources to the static resources provided via the descriptor.
     *
     * @param staticResourceDescriptors
     *          the resources to add
     *
     * @return this builder for chaining
     */
    public RestServiceDescriptorBuilder addStaticResources(
        @NonNull final Iterable<? extends StaticResourceDescriptor> staticResourceDescriptors)
    {
      staticResourceDescriptors.forEach(this.staticResources::add);
      return this;
    }

    /**
     * Adds static resources to the static resources provided via the descriptor.
     *
     * @param staticResourceDescriptors
     *          the resources to add
     *
     * @return this builder for chaining
     */
    public RestServiceDescriptorBuilder addStaticResources(
        @NonNull final StaticResourceDescriptor... staticResourceDescriptors)
    {
      Collections.addAll(this.staticResources, staticResourceDescriptors);
      return this;
    }

    // Hidden because of Lombok
    @SuppressWarnings("unused")
    private RestServiceDescriptorBuilder singletons(final Set<Object> singletons)
    {
      return this;
    }

    // Hidden because of Lombok
    @SuppressWarnings("unused")
    private RestServiceDescriptorBuilder staticResources(
        final List<StaticResourceDescriptor> resources)
    {
      return this;
    }

    // Hidden because of Lombok
    @SuppressWarnings("unused")
    private RestServiceDescriptorBuilder filters(
        final List<StaticResourceDescriptor> filters)
    {
      return this;
    }
  }
}

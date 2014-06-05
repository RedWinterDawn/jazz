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
  private final Set<Object> resources;

  private final Set<Object> providers;

  private final List<FilterMappingDescriptor> filters;

  private final Set<StaticResourceDescriptor> staticResources;

  @Builder
  private RestServiceDescriptor(
      @NonNull final Set<Object> resources,
      @NonNull final Set<Object> providers,
      @NonNull final List<FilterMappingDescriptor> filters,
      @NonNull final List<StaticResourceDescriptor> staticResources)
  {
    this.resources = Collections.unmodifiableSet(new HashSet<>(resources));
    this.providers = Collections.unmodifiableSet(new HashSet<>(providers));
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
    private final Set<Object> resources = new HashSet<>();

    private final Set<Object> providers = new HashSet<>();

    private final List<FilterMappingDescriptor> filters = new LinkedList<>();

    private final List<StaticResourceDescriptor> staticResources = new LinkedList<>();

    /**
     * Adds a resource to the resources provided via the descriptor.
     * <p>
     * If a resource that {@link Object#equals(Object) equals} {@code resource} has already been
     * configured, this call overwrites the previous value.
     *
     * @param resource
     *          the resource to add
     *
     * @return this builder for chaining
     */
    public RestServiceDescriptorBuilder addResource(final Object resource)
    {
      resources.add(resource);
      return this;
    }

    /**
     * Adds resources to the resources provided via the descriptor.
     * <p>
     * If a resource that {@link Object#equals(Object) equals} a value provided by {@code resources}
     * has already been configured, this call overwrites the previous value.
     *
     * @param resources
     *          the resources to add
     *
     * @return this builder for chaining
     */
    public RestServiceDescriptorBuilder addResources(
        @NonNull final Iterable<? extends Object> resources)
    {
      resources.forEach(this.resources::add);
      return this;
    }

    /**
     * Adds resources to the resources provided via the descriptor.
     * <p>
     * If a resource that {@link Object#equals(Object) equals} a value provided by {@code resources}
     * has already been configured, this call overwrites the previous value.
     *
     * @param resources
     *          the resources to add
     *
     * @return this builder for chaining
     */
    public RestServiceDescriptorBuilder addResources(@NonNull final Object... resources)
    {
      Collections.addAll(this.resources, resources);
      return this;
    }

    /**
     * Adds a provider to the resources provided via the descriptor.
     * <p>
     * If a resource that {@link Object#equals(Object) equals} {@code provider} has already been
     * configured, this call overwrites the previous value.
     *
     * @param provider
     *          the provider to add
     *
     * @return this builder for chaining
     */
    public RestServiceDescriptorBuilder addProvider(final Object provider)
    {
      providers.add(provider);
      return this;
    }

    /**
     * Adds providers to the resources provided via the descriptor.
     * <p>
     * If a provider that {@link Object#equals(Object) equals} a value provided by {@code providers}
     * has already been configured, this call overwrites the previous value.
     *
     * @param providers
     *          the providers to add
     *
     * @return this builder for chaining
     */
    public RestServiceDescriptorBuilder addProviders(
        @NonNull final Iterable<? extends Object> providers)
    {
      providers.forEach(this.providers::add);
      return this;
    }

    /**
     * Adds providers to the resources provided via the descriptor.
     * <p>
     * If a provider that {@link Object#equals(Object) equals} a value provided by {@code providers}
     * has already been configured, this call overwrites the previous value.
     *
     * @param resources
     *          the resources to add
     *
     * @return this builder for chaining
     */
    public RestServiceDescriptorBuilder addProviders(@NonNull final Object... providers)
    {
      Collections.addAll(this.providers, providers);
      return this;
    }


    /**
     * Adds a filter to the filters provided via the descriptor.
     * <p>
     * Filters are provided in the order in which they are added to this builder.
     *
     * @param filter
     *          the filter to add
     *
     * @return this builder for chaining
     */
    public RestServiceDescriptorBuilder addFilter(
        final FilterMappingDescriptor filterMappingDescriptor)
    {
      filters.add(filterMappingDescriptor);
      return this;
    }

    /**
     * Adds a filter to the filters provided via the descriptor.
     *
     * @param filterMappingDescriptors
     *          the filters to add
     *
     * @return this builder for chaining
     */
    public RestServiceDescriptorBuilder addFilters(
        @NonNull final Iterable<? extends FilterMappingDescriptor> filterMappingDescriptors)
    {
      filterMappingDescriptors.forEach(this.filters::add);
      return this;
    }

    /**
     * Adds filters to the filters provided via the descriptor.
     *
     * @param filterMappingDescriptors
     *          the filters to add
     *
     * @return this builder for chaining
     */
    public RestServiceDescriptorBuilder addFilters(
        @NonNull final FilterMappingDescriptor... filterMappingDescriptors)
    {
      Collections.addAll(this.filters, filterMappingDescriptors);
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
    private RestServiceDescriptorBuilder resources(final Set<Object> resources)
    {
      return this;
    }

    // Hidden because of Lombok
    @SuppressWarnings("unused")
    private RestServiceDescriptorBuilder providers(final Set<Object> providers)
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

package com.jive.myco.jazz.api.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Builder;

/**
 * Represents a collection of Web resources that may be bound in a {@link HttpServerManager}.
 *
 * @author David Valeri
 */
@Getter
public final class WebAppDescriptor
{
  private final List<ServletMappingDescriptor> servlets;

  private final List<FilterMappingDescriptor> filters;

  private final List<StaticResourceDescriptor> staticResources;

  private final boolean includeJazzContextFilter;

  private final boolean includeJazzMdcFilter;

  private final boolean includeJazzContextEnhancerRulesFilter;

  @Builder
  private WebAppDescriptor(
      @NonNull final List<ServletMappingDescriptor> servlets,
      @NonNull final List<FilterMappingDescriptor> filters,
      @NonNull final List<StaticResourceDescriptor> staticResources,
      final boolean includeJazzContextFilter,
      final boolean includeJazzMdcFilter,
      final boolean includeJazzContextEnhancerRulesFilter)
  {
    this.servlets =
        Collections.unmodifiableList(new ArrayList<>(servlets));
    this.filters =
        Collections.unmodifiableList(new ArrayList<>(filters));
    this.staticResources =
        Collections.unmodifiableList(new ArrayList<>(staticResources));
    this.includeJazzContextFilter = includeJazzContextFilter;
    this.includeJazzMdcFilter = includeJazzMdcFilter;
    this.includeJazzContextEnhancerRulesFilter = includeJazzContextEnhancerRulesFilter;
  }

  /**
   * A builder for instances of {@link WebAppDescriptor}.
   *
   * @author David Valeri
   */
  public static final class WebAppDescriptorBuilder
  {
    private final List<ServletMappingDescriptor> servlets = new LinkedList<>();
    private final List<FilterMappingDescriptor> filters = new LinkedList<>();
    private final List<StaticResourceDescriptor> staticResources = new LinkedList<>();
    private boolean includeJazzContextFilter = true;
    private boolean includeJazzMdcFilter = true;
    private boolean includeJazzContextEnhancerRulesFilter = true;

    /**
     * Adds a servlet to the servlets provided via the descriptor.
     *
     * @param servletMappingDescriptor
     *          the descriptor for the servlet to add
     *
     * @return this builder for chaining
     */
    public WebAppDescriptorBuilder addServlet(
        final ServletMappingDescriptor servletMappingDescriptor)
    {
      servlets.add(servletMappingDescriptor);
      return this;
    }

    /**
     * Adds a filter to the filters provided via the descriptor.
     *
     * @param filter
     *          the filter to add
     *
     * @return this builder for chaining
     */
    public WebAppDescriptorBuilder addFilter(
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
    public WebAppDescriptorBuilder addFilters(
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
    public WebAppDescriptorBuilder addFilters(
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
    public WebAppDescriptorBuilder addStaticResource(
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
    public WebAppDescriptorBuilder addStaticResources(
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
    public WebAppDescriptorBuilder addStaticResources(
        @NonNull final StaticResourceDescriptor... staticResourceDescriptors)
    {
      Collections.addAll(this.staticResources, staticResourceDescriptors);
      return this;
    }

    // Hidden due to Lombok
    @SuppressWarnings("unused")
    private WebAppDescriptorBuilder servlets(
        final List<ServletMappingDescriptor> servletMappingDescriptors)
    {
      return this;
    }

    // Hidden due to Lombok
    @SuppressWarnings("unused")
    private WebAppDescriptorBuilder staticResources(
        final List<StaticResourceDescriptor> staticResourceDescriptors)
    {
      return this;
    }

    // Hidden due to Lombok
    @SuppressWarnings("unused")
    private WebAppDescriptorBuilder filters(
        final List<FilterMappingDescriptor> filterMappingDescriptors)
    {
      return this;
    }
  }
}

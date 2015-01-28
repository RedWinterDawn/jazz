package com.jive.myco.jazz.api.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.MultipartConfigElement;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Builder;

import com.jive.myco.jazz.api.core.network.ConnectorDescriptor;
import com.jive.myco.jazz.api.registry.AutoRegisteredServiceInstanceDescriptor;
import com.jive.myco.jazz.api.web.FilterMappingDescriptor;
import com.jive.myco.jazz.api.web.StaticResourceDescriptor;

/**
 * Represents the components that comprise a REST service. See the {@link #builder() builder} for
 * more information on constructing a valid descriptor.
 *
 * @author David Valeri
 */
public final class RestServiceDescriptor
{
  private final AtomicInteger INSTANCE_COUNTER = new AtomicInteger();

  @Getter
  private final String id;

  @Getter
  private final Set<Object> singletons;

  @Getter
  private final List<FilterMappingDescriptor> filters;

  @Getter
  private final Set<StaticResourceDescriptor> staticResources;

  @Getter
  private final boolean includeJazzContextFilter;

  @Getter
  private final boolean includeJazzMdcFilter;

  @Getter
  private final boolean includeJazzContextEnhancerRulesFilter;

  @Getter
  private final boolean includeJazzHttpRequestContextFilter;

  /**
   * An optional multipart config element used to configure multi-part support on the REST service.
   */
  @Getter
  private final MultipartConfigElement multipartConfigElement;

  @Getter
  private final boolean enableMetrics;

  /**
   * Indicates if all JAX-RS resource methods exposed via this descriptor should be metered, timed,
   * and exception metered regardless of if they are annotated with the respective annotation.
   */
  @Getter
  private final boolean forceMetrics;

  @Getter
  private final AutoRegisteredServiceInstanceDescriptor autoRegisteredServiceInstanceDescriptor;

  @Getter
  private final String contextPath;

  @Getter
  private final List<ConnectorDescriptor> connectorDescriptors;

  @Builder
  private RestServiceDescriptor(
      final String id,
      @NonNull final Set<Object> singletons,
      @NonNull final List<FilterMappingDescriptor> filters,
      @NonNull final List<StaticResourceDescriptor> staticResources,
      final boolean includeJazzContextFilter,
      final boolean includeJazzMdcFilter,
      final boolean includeJazzContextEnhancerRulesFilter,
      final boolean includeJazzHttpRequestContextFilter,
      final MultipartConfigElement multipartConfigElement,
      final boolean enableMetrics,
      final boolean forceMetrics,
      final AutoRegisteredServiceInstanceDescriptor autoRegisteredServiceInstanceDescriptor,
      final String contextPath,
      @NonNull final List<ConnectorDescriptor> connectorDescriptors)
  {
    this.id = id == null ? "rest-service-" + INSTANCE_COUNTER.getAndIncrement() : id;
    this.singletons = Collections.unmodifiableSet(new HashSet<>(singletons));
    this.staticResources =
        Collections.unmodifiableSet(new HashSet<>(staticResources));
    this.filters =
        Collections.unmodifiableList(new ArrayList<>(filters));
    this.includeJazzContextFilter = includeJazzContextFilter;
    this.includeJazzMdcFilter = includeJazzMdcFilter;
    this.includeJazzContextEnhancerRulesFilter = includeJazzContextEnhancerRulesFilter;
    this.includeJazzHttpRequestContextFilter = includeJazzHttpRequestContextFilter;
    this.multipartConfigElement = multipartConfigElement;
    this.enableMetrics = enableMetrics;
    this.forceMetrics = forceMetrics;
    this.autoRegisteredServiceInstanceDescriptor = autoRegisteredServiceInstanceDescriptor;
    this.contextPath = contextPath;
    this.connectorDescriptors = Collections.unmodifiableList(new ArrayList<>(connectorDescriptors));
  }

  /**
   * A builder for creating {@link RestServiceDescriptor} instances.
   *
   * @author David Valeri
   */
  public static final class RestServiceDescriptorBuilder implements
      FluentRestServiceDescriptorBuilder<RestServiceDescriptorBuilder>
  {
    private final Set<Object> singletons = new HashSet<>();

    private final List<FilterMappingDescriptor> filters = new LinkedList<>();

    private final List<StaticResourceDescriptor> staticResources = new LinkedList<>();

    private boolean includeJazzContextFilter = true;

    private boolean includeJazzMdcFilter = true;

    private boolean includeJazzContextEnhancerRulesFilter = true;

    private boolean includeJazzHttpRequestContextFilter = true;

    private boolean enableMetrics = true;

    private final List<ConnectorDescriptor> connectorDescriptors = new LinkedList<>();

    @Override
    public RestServiceDescriptorBuilder addSingleton(final Object singleton)
    {
      singletons.add(singleton);
      return this;
    }

    @Override
    public RestServiceDescriptorBuilder addSingletons(
        @NonNull final Iterable<? extends Object> additionalSingletons)
    {
      additionalSingletons.forEach(singletons::add);
      return this;
    }

    @Override
    public RestServiceDescriptorBuilder addSingletons(@NonNull final Object... additionalSingletons)
    {
      Collections.addAll(singletons, additionalSingletons);
      return this;
    }

    @Override
    public RestServiceDescriptorBuilder addFilter(final FilterMappingDescriptor filterDescriptor)
    {
      filters.add(filterDescriptor);
      return this;
    }

    @Override
    public RestServiceDescriptorBuilder addFilters(
        @NonNull final Iterable<? extends FilterMappingDescriptor> filterDescriptors)
    {
      filterDescriptors.forEach(this.filters::add);
      return this;
    }

    @Override
    public RestServiceDescriptorBuilder addFilters(
        @NonNull final FilterMappingDescriptor... filterDescriptors)
    {
      Collections.addAll(this.filters, filterDescriptors);
      return this;
    }

    @Override
    public RestServiceDescriptorBuilder addStaticResource(
        @NonNull final StaticResourceDescriptor staticResourceDescriptor)
    {
      staticResources.add(staticResourceDescriptor);
      return this;
    }

    @Override
    public RestServiceDescriptorBuilder addStaticResources(
        @NonNull final Iterable<? extends StaticResourceDescriptor> staticResourceDescriptors)
    {
      staticResourceDescriptors.forEach(this.staticResources::add);
      return this;
    }

    @Override
    public RestServiceDescriptorBuilder addStaticResources(
        @NonNull final StaticResourceDescriptor... staticResourceDescriptors)
    {
      Collections.addAll(this.staticResources, staticResourceDescriptors);
      return this;
    }

    @Override
    public RestServiceDescriptorBuilder register(
        final AutoRegisteredServiceInstanceDescriptor autoRegisteredServiceInstanceDescriptor)
    {
      this.autoRegisteredServiceInstanceDescriptor = autoRegisteredServiceInstanceDescriptor;
      return this;
    }

    @Override
    public RestServiceDescriptorBuilder contextPath(final String contextPath)
    {
      this.contextPath = contextPath;
      return this;
    }

    @Override
    public RestServiceDescriptorBuilder addConnector(
        @NonNull final ConnectorDescriptor connectorDescriptor)
    {
      connectorDescriptors.add(connectorDescriptor);
      return this;
    }

    @Override
    public RestServiceDescriptorBuilder addConnectors(
        final Iterable<? extends ConnectorDescriptor> connectorDescriptors)
    {
      connectorDescriptors.forEach(this.connectorDescriptors::add);
      return this;
    }

    // Hidden because of Lombok
    @SuppressWarnings("unused")
    private RestServiceDescriptorBuilder singletons(final Set<Object> additionalSingletons)
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
        final List<StaticResourceDescriptor> additionalFilters)
    {
      return this;
    }

    // Hidden due to Lombok
    @SuppressWarnings("unused")
    private RestServiceDescriptorBuilder autoRegisteredServiceInstanceDescriptor(
        final AutoRegisteredServiceInstanceDescriptor autoRegisteredServiceInstanceDescriptor)
    {
      return this;
    }

    // Hidden due to Lombok
    @SuppressWarnings("unused")
    private RestServiceDescriptorBuilder connectorDescriptors(
        final List<ConnectorDescriptor> connectorDescriptors)
    {
      return this;
    }
  }
}

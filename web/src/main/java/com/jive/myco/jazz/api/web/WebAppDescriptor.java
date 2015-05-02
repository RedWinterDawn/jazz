package com.jive.myco.jazz.api.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import com.jive.myco.jazz.api.core.network.ConnectorDescriptor;
import com.jive.myco.jazz.api.registry.AbstractConnectedAndRegisteredBindingGracefulShutdownHook;
import com.jive.myco.jazz.api.registry.AutoRegisteredServiceInstanceDescriptor;
import com.jive.myco.jazz.api.registry.ConnectedAndRegisteredBindingGracefulShutdownHook;

/**
 * Represents a collection of Web resources that may be bound in a {@link HttpServerManager}.
 *
 * @author David Valeri
 */
public final class WebAppDescriptor
{
  private static final AbstractConnectedAndRegisteredBindingGracefulShutdownHook NULL_SHUTDOWN_HOOK =
      new AbstractConnectedAndRegisteredBindingGracefulShutdownHook() {/* */};

  private final AtomicInteger INSTANCE_COUNTER = new AtomicInteger();

  @Getter
  private final String id;

  @Getter
  private final List<ServletMappingDescriptor> servlets;

  @Getter
  private final List<FilterMappingDescriptor> filters;

  @Getter
  private final List<StaticResourceDescriptor> staticResources;

  @Getter
  private final boolean includeJazzContextFilter;

  @Getter
  private final boolean includeJazzMdcFilter;

  @Getter
  private final boolean includeJazzContextEnhancerRulesFilter;

  @Getter
  private final boolean includeJazzHttpRequestContextFilter;

  @Getter
  private final AutoRegisteredServiceInstanceDescriptor autoRegisteredServiceInstanceDescriptor;

  @Getter
  private final String contextPath;

  @Getter
  private final List<ConnectorDescriptor> connectorDescriptors;

  @Getter
  private final ConnectedAndRegisteredBindingGracefulShutdownHook gracefulShutdownHook;

  @Builder
  private WebAppDescriptor(
      final String id,
      @NonNull final List<ServletMappingDescriptor> servlets,
      @NonNull final List<FilterMappingDescriptor> filters,
      @NonNull final List<StaticResourceDescriptor> staticResources,
      final boolean includeJazzContextFilter,
      final boolean includeJazzMdcFilter,
      final boolean includeJazzContextEnhancerRulesFilter,
      final boolean includeJazzHttpRequestContextFilter,
      final AutoRegisteredServiceInstanceDescriptor autoRegisteredServiceInstanceDescriptor,
      final String contextPath,
      @NonNull final List<ConnectorDescriptor> connectorDescriptors,
      final ConnectedAndRegisteredBindingGracefulShutdownHook gracefulShutdownHook)
  {
    this.id = id == null ? "web-app-" + INSTANCE_COUNTER.getAndIncrement() : id;
    this.servlets =
        Collections.unmodifiableList(new ArrayList<>(servlets));
    this.filters =
        Collections.unmodifiableList(new ArrayList<>(filters));
    this.staticResources =
        Collections.unmodifiableList(new ArrayList<>(staticResources));
    this.includeJazzContextFilter = includeJazzContextFilter;
    this.includeJazzMdcFilter = includeJazzMdcFilter;
    this.includeJazzContextEnhancerRulesFilter = includeJazzContextEnhancerRulesFilter;
    this.includeJazzHttpRequestContextFilter = includeJazzHttpRequestContextFilter;
    this.autoRegisteredServiceInstanceDescriptor = autoRegisteredServiceInstanceDescriptor;
    this.contextPath = contextPath;
    this.connectorDescriptors = Collections.unmodifiableList(new ArrayList<>(connectorDescriptors));
    this.gracefulShutdownHook = gracefulShutdownHook == null
        ? NULL_SHUTDOWN_HOOK : gracefulShutdownHook;
  }

  /**
   * A builder for instances of {@link WebAppDescriptor}.
   *
   * @author David Valeri
   */
  public static final class WebAppDescriptorBuilder implements
      FluentWebAppDescriptorBuilder<WebAppDescriptorBuilder>
  {
    private final List<ConnectorDescriptor> connectorDescriptors = new LinkedList<>();
    private final List<FilterMappingDescriptor> filters = new LinkedList<>();
    private final List<ServletMappingDescriptor> servlets = new LinkedList<>();
    private final List<StaticResourceDescriptor> staticResources = new LinkedList<>();

    private boolean includeJazzContextFilter = true;
    private boolean includeJazzMdcFilter = true;
    private boolean includeJazzContextEnhancerRulesFilter = true;
    private boolean includeJazzHttpRequestContextFilter = true;

    @Override
    public WebAppDescriptorBuilder addConnector(
        @NonNull final ConnectorDescriptor connectorDescriptor)
    {
      connectorDescriptors.add(connectorDescriptor);
      return this;
    }

    @Override
    public WebAppDescriptorBuilder addConnectors(
        final Iterable<? extends ConnectorDescriptor> connectorDescriptors)
    {
      connectorDescriptors.forEach(this.connectorDescriptors::add);
      return this;
    }

    @Override
    public WebAppDescriptorBuilder addConnectors(
        @NonNull final ConnectorDescriptor... connectorDescriptors)
    {
      Collections.addAll(this.connectorDescriptors, connectorDescriptors);
      return this;
    }

    @Override
    public WebAppDescriptorBuilder addFilter(
        final FilterMappingDescriptor filterMappingDescriptor)
    {
      filters.add(filterMappingDescriptor);
      return this;
    }

    @Override
    public WebAppDescriptorBuilder addFilters(
        @NonNull final Iterable<? extends FilterMappingDescriptor> filterMappingDescriptors)
    {
      filterMappingDescriptors.forEach(this.filters::add);
      return this;
    }

    @Override
    public WebAppDescriptorBuilder addFilters(
        @NonNull final FilterMappingDescriptor... filterMappingDescriptors)
    {
      Collections.addAll(this.filters, filterMappingDescriptors);
      return this;
    }

    @Override
    public WebAppDescriptorBuilder addServlet(
        final ServletMappingDescriptor servletMappingDescriptor)
    {
      servlets.add(servletMappingDescriptor);
      return this;
    }

    @Override
    public WebAppDescriptorBuilder addStaticResource(
        @NonNull final StaticResourceDescriptor staticResourceDescriptor)
    {
      staticResources.add(staticResourceDescriptor);
      return this;
    }

    @Override
    public WebAppDescriptorBuilder addStaticResources(
        @NonNull final Iterable<? extends StaticResourceDescriptor> staticResourceDescriptors)
    {
      staticResourceDescriptors.forEach(this.staticResources::add);
      return this;
    }

    @Override
    public WebAppDescriptorBuilder addStaticResources(
        @NonNull final StaticResourceDescriptor... staticResourceDescriptors)
    {
      Collections.addAll(this.staticResources, staticResourceDescriptors);
      return this;
    }

    @Override
    public WebAppDescriptorBuilder register(
        final AutoRegisteredServiceInstanceDescriptor autoRegisteredServiceInstanceDescriptor)
    {
      this.autoRegisteredServiceInstanceDescriptor = autoRegisteredServiceInstanceDescriptor;
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

    // Hidden due to Lombok
    @SuppressWarnings("unused")
    private WebAppDescriptorBuilder autoRegisteredServiceInstanceDescriptor(
        final AutoRegisteredServiceInstanceDescriptor autoRegisteredServiceInstanceDescriptor)
    {
      return this;
    }

    // Hidden due to Lombok
    @SuppressWarnings("unused")
    private WebAppDescriptorBuilder connectorDescriptors(
        final List<ConnectorDescriptor> connectorDescriptors)
    {
      return this;
    }
  }
}

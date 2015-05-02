package com.jive.myco.jazz.api.registry;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * @author David Valeri
 */
@Getter
public class ServiceInstanceSupplierSelectorCriteria
{
  public static final ServiceInstanceSupplierSelectorCriteria EMPTY_CRITERIA =
      ServiceInstanceSupplierSelectorCriteria.builder().build();

  private final Set<String> deprioritizedServiceInstanceIds;
  private final Set<String> deprioritizedServiceAddresses;

  @Builder
  public ServiceInstanceSupplierSelectorCriteria(
      @NonNull final Set<String> deprioritizedServiceInstanceIds,
      final Set<String> deprioritizedServiceAddresses)
  {
    this.deprioritizedServiceInstanceIds = new HashSet<>(deprioritizedServiceInstanceIds);
    this.deprioritizedServiceAddresses = deprioritizedServiceAddresses;
  }

  public static final class ServiceInstanceSupplierSelectorCriteriaBuilder
  {
    private final Set<String> deprioritizedServiceInstanceIds = new HashSet<>();
    private final Set<String> deprioritizedServiceAddresses = new HashSet<>();

    public ServiceInstanceSupplierSelectorCriteriaBuilder deprioritizedServiceInstanceIds(
        final Collection<? extends String> deprioritizedServiceInstanceIds)
    {
      this.deprioritizedServiceInstanceIds.clear();
      this.deprioritizedServiceInstanceIds.addAll(deprioritizedServiceInstanceIds);
      return this;
    }

    public ServiceInstanceSupplierSelectorCriteriaBuilder deprioritizedServiceAddresses(
        final Collection<? extends String> deprioritizedServiceAddresses)
    {
      this.deprioritizedServiceAddresses.clear();
      this.deprioritizedServiceAddresses.addAll(deprioritizedServiceAddresses);
      return this;
    }
  }
}

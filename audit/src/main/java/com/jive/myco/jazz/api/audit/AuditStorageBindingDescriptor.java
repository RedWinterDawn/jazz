package com.jive.myco.jazz.api.audit;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * A descriptor for creating an {@link AuditStorageBinding} instance.
 *
 * @author David Valeri
 */
@Builder
@Getter
@ToString
public class AuditStorageBindingDescriptor
{
  private final List<AuditEventSerializer> auditEventSerializers;

  private AuditStorageBindingDescriptor(
      @NonNull final List<AuditEventSerializer> auditEventSerializers)
  {
    this.auditEventSerializers = auditEventSerializers;
  }

  public static final class AuditStorageBindingDescriptorBuilder
  {
    private final List<AuditEventSerializer> auditEventSerializers = new LinkedList<>();

    public AuditStorageBindingDescriptorBuilder auditEventSerializers(
        final Collection<? extends AuditEventSerializer> auditEventSerializers)
    {
      this.auditEventSerializers.clear();
      addAuditEventSerializers(auditEventSerializers);

      return this;
    }

    public AuditStorageBindingDescriptorBuilder addAuditEventSerializers(
        final Iterable<? extends AuditEventSerializer> auditEventSerializers)
    {
      auditEventSerializers.forEach((aes) -> this.auditEventSerializers.add(aes));

      return this;
    }

    public AuditStorageBindingDescriptorBuilder addAuditEventSerializers(
        final AuditEventSerializer... auditEventSerializers)
    {
      addAuditEventSerializers(Arrays.asList(auditEventSerializers));
      return this;
    }

    public AuditStorageBindingDescriptorBuilder addAuditEventSerializer(
        final AuditEventSerializer auditEventSerializer)
    {
      auditEventSerializers.add(auditEventSerializer);
      return this;
    }
  }
}

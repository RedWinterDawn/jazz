package com.jive.myco.jazz.api.audit;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class AuditServiceDescriptor
{
  private final String id;

  private final List<AuditEventSerializer> auditEventSerializers;

  private final AuditEventDecorator auditEventDecorator;

  @Builder
  private AuditServiceDescriptor(
      @NonNull final String id,
      @NonNull final List<AuditEventSerializer> auditEventSerializers,
      final AuditEventDecorator auditEventDecorator)
  {
    this.id = id;
    this.auditEventSerializers = auditEventSerializers;
    this.auditEventDecorator = auditEventDecorator;
  }

  public static final class AuditServiceDescriptorBuilder
  {
    private List<AuditEventSerializer> auditEventSerializers = new LinkedList<>();

    public AuditServiceDescriptorBuilder addAuditEventSerializer(final AuditEventSerializer serializer)
    {
      auditEventSerializers.add(serializer);
      return this;
    }

    public AuditServiceDescriptorBuilder addAuditEventSerializers(
        final Iterable<? extends AuditEventSerializer> auditEventSerializers)
    {
      auditEventSerializers.forEach(this.auditEventSerializers::add);
      return this;
    }

    public AuditServiceDescriptorBuilder addAuditEventSerializers(
        final AuditEventSerializer... auditEventSerializers)
    {
      addAuditEventSerializers(Arrays.asList(auditEventSerializers));
      return this;
    }
  }
}

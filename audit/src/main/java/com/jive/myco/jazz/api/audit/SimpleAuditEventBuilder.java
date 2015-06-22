package com.jive.myco.jazz.api.audit;

import java.util.Map;

import lombok.Data;
import lombok.experimental.Accessors;

import org.joda.time.Instant;

/**
 * A simple builder for AuditEvent.
 * @author Binh Tran
 */
@Accessors(fluent = true)
@Data
public class SimpleAuditEventBuilder implements AuditEventBuilder
{
  /**
   * The audit event type/category of the data.
   */
  private String type;

  /**
   * The instant this audit event was created.
   */
  private Instant created;

  /**
   * The auditing component which generated this event.
   */
  private String component;

  /**
   * the name of this event.
   */
  private String name;

  /**
   * meta data provided outside the scope of the audit generator (e.g, MDC).
   */
  private Map<String, Object> meta;


  /**
   * Audit event data, which may be @Value based.
   *
   * This does not include metadata around the environment, e.g calling thread etc.
   *
   */
  private Map<String, Object> data;

  @Override
  public AuditEvent build()
  {
    return new SimpleAuditEvent(type, created, component, name, meta, data);
  }
}

package com.jive.myco.jazz.api.audit;

import java.util.Map;

import org.joda.time.Instant;

/**
 * @author Binh Tran
 */
public interface AuditEvent
{
  /**
   * The audit event type/category of the data.
   */
  String getType();

  /**
   * The instant this audit event was created.
   */
  Instant getCreated();

  /**
   * The auditing component which generated this event.
   */
  String getComponent();

  /**
   * The name of this event.
   */
  String getName();

  /**
   * Meta data provided outside the scope of the audit generator (e.g, MDC).
   */
  Map<String, Object> getMeta();

  /**
   * Audit event data, which may be @Value based.
   *
   * This does not include meta data around the environment, e.g calling thread etc.
   */
  Map<String, Object> getData();
}

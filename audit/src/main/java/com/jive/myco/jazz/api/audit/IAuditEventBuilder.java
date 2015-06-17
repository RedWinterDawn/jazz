package com.jive.myco.jazz.api.audit;

import java.util.Map;

import org.joda.time.Instant;

/**
 * @author Binh Tran
 */
public interface IAuditEventBuilder
{
  IAuditEvent build();

  String type();

  Instant created();

  String component();

  String name();

  Map<String, Object> meta();

  Map<String, Object> data();

  AuditEventBuilder type(String type);

  AuditEventBuilder created(Instant created);

  AuditEventBuilder component(String component);

  AuditEventBuilder name(String name);

  AuditEventBuilder meta(Map<String, Object> meta);

  AuditEventBuilder data(Map<String, Object> data);
}

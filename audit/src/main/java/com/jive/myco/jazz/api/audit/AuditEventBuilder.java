package com.jive.myco.jazz.api.audit;

import java.util.Map;

import org.joda.time.Instant;

/**
 * @author Binh Tran
 */
public interface AuditEventBuilder
{
  AuditEvent build();

  String type();

  Instant created();

  String component();

  String name();

  Map<String, Object> meta();

  Map<String, Object> data();

  SimpleAuditEventBuilder type(String type);

  SimpleAuditEventBuilder created(Instant created);

  SimpleAuditEventBuilder component(String component);

  SimpleAuditEventBuilder name(String name);

  SimpleAuditEventBuilder meta(Map<String, Object> meta);

  SimpleAuditEventBuilder data(Map<String, Object> data);
}

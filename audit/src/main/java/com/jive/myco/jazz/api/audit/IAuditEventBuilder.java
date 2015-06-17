package com.jive.myco.jazz.api.audit;

/**
 * @author Binh Tran
 */
public interface IAuditEventBuilder
{
  IAuditEvent build();

  String type();

  org.joda.time.Instant created();

  String component();

  String name();

  java.util.Map<String, Object> meta();

  java.util.Map<String, Object> data();

  AuditEventBuilder type(String type);

  AuditEventBuilder created(org.joda.time.Instant created);

  AuditEventBuilder component(String component);

  AuditEventBuilder name(String name);

  AuditEventBuilder meta(java.util.Map<String, Object> meta);

  AuditEventBuilder data(java.util.Map<String, Object> data);
}

package com.jive.myco.jazz.api.audit;

/**
 * @author Binh Tran
 */
public interface IAuditEvent
{
  String getType();

  org.joda.time.Instant getCreated();

  String getComponent();

  String getName();

  java.util.Map<String, Object> getMeta();

  java.util.Map<String, Object> getData();
}

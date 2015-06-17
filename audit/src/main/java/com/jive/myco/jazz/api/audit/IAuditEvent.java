package com.jive.myco.jazz.api.audit;

import java.util.Map;

import org.joda.time.Instant;

/**
 * @author Binh Tran
 */
public interface IAuditEvent
{
  String getType();

  Instant getCreated();

  String getComponent();

  String getName();

  Map<String, Object> getMeta();

  Map<String, Object> getData();
}

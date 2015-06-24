package com.jive.myco.jazz.api.audit;

import java.util.concurrent.TimeUnit;

/**
 * @author Binh Tran
 */
public interface AuditService
{
  AuditEventDecorator getDecorator();
  void submit(final AuditEvent auditEvent);
}

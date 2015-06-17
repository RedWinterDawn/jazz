package com.jive.myco.jazz.api.audit;

import java.util.concurrent.TimeUnit;

/**
 * @author Binh Tran
 */
public interface IAuditService
{
  IAuditEventDecorator getDecorator();
  void addStorage(final IAuditStorage storage);
  void submit(final IAuditEvent auditEvent);
  void flush(final TimeUnit unit, final long duration);
}

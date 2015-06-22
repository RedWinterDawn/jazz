package com.jive.myco.jazz.api.audit;

/**
 * @author Binh Tran
 */
public interface AuditStorage
{
  void submit(AuditEvent auditEvent);
}

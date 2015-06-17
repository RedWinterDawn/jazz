package com.jive.myco.jazz.api.audit;

/**
 * @author Binh Tran
 */
public interface IAuditStorage
{
  void submit(IAuditEvent auditEvent);
}

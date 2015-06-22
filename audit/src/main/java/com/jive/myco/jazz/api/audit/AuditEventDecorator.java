package com.jive.myco.jazz.api.audit;

/**
 * @author Binh Tran
 */
public interface AuditEventDecorator
{
  AuditEvent decorate (AuditEvent event);
}

package com.jive.myco.jazz.api.audit;

/**
 * @author Binh Tran
 */
public interface IAuditEventDecorator
{
  IAuditEvent decorate(IAuditEvent event);
}

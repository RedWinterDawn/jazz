package com.jive.myco.jazz.api.audit;

/**
 * @author Binh Tran
 */
public interface AuditEventDecorator
{
  AuditEvent decorate(final AuditEvent event);

  default AuditEventDecorator decorate(final AuditEventDecorator decorator)
  {
    if (decorator != null)
    {
      return (ae) -> decorate(decorator.decorate(ae));
    }
    else
    {
      return this;
    }
  }
}

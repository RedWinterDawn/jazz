package com.jive.myco.jazz.api.audit;

/**
 * @author Binh Tran
 */
public interface AuditEventDecorator
{
  AuditEvent decorate(final AuditEvent event);
  default AuditEvent composeWith(final AuditEventDecorator decorator, final AuditEvent event)
  {
    if (decorator != null)
    {
      return decorate(decorator.decorate(event));
    }
    else
    {
      return decorate(event);
    }
  }
}

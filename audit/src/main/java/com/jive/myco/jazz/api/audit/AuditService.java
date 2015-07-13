package com.jive.myco.jazz.api.audit;

import com.jive.myco.commons.concurrent.PnkyPromise;

/**
 * @author Binh Tran
 */
public interface AuditService
{
  /**
   * Submit an event to the audit service.
   *
   * @param auditEvent
   *          the event to record
   *
   * @return a promise that resolves when the event is written to the audit service
   */
  PnkyPromise<Void> submit(final AuditEvent auditEvent);
}

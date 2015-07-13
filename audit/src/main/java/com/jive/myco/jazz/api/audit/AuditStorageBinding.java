package com.jive.myco.jazz.api.audit;

import com.jive.myco.commons.concurrent.PnkyPromise;

/**
 * A binding to an {@link AuditStorage} instance.
 *
 * @author David Valeri
 */
public interface AuditStorageBinding
{
  /**
   * Submit an event to the audit service.
   *
   * @param auditEvent
   *          the event to record
   *
   * @return a promise that resolves when the event is written to the audit storage
   */
  PnkyPromise<Void> submit(final AuditEvent auditEvent);
}

package com.jive.myco.jazz.api.audit;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.lifecycle.ListenableLifecycled;

/**
 * @author Binh Tran
 */
public interface AuditServiceManager extends ListenableLifecycled
{
  /**
   * Creates an {@link AuditService} instance based on the internal configuration of this manager
   * and the configuration options in the supplied descriptor.
   *
   * @param auditServiceDescriptor
   *          the descriptor used to further configure the audit service
   *
   * @return a promise that resolves with the created audit service on success or an
   *         {@link IllegalStateException} if this manager is not initialized
   */
  PnkyPromise<AuditService> createAuditService(final AuditServiceDescriptor auditServiceDescriptor);
}

package com.jive.myco.jazz.api.audit;

import com.jive.myco.commons.concurrent.PnkyPromise;
import com.jive.myco.commons.lifecycle.ListenableLifecycled;

/**
 * @author Binh Tran
 */
public interface AuditStorage extends ListenableLifecycled
{
  /**
   * Returns the identifier for this instance.
   */
  String getId();

  /**
   * Creates a binding to this instance, resolving configuration based on this instance and the
   * configuration in the supplied descriptor.
   *
   * @param auditStorageBindingDescriptor
   *          the descriptor used to configure the binding
   *
   * @return a promise resolving to the created binding or rejecting with an
   *         {@link IllegalStateException} if this instance is not initialized
   */
  PnkyPromise<AuditStorageBinding> bind(
      final AuditStorageBindingDescriptor auditStorageBindingDescriptor);
}

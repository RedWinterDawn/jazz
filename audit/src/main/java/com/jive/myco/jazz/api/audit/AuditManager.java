package com.jive.myco.jazz.api.audit;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Binh Tran
 */
public interface AuditManager
{
  AuditService segment(final ObjectMapper mapper, final String segment, final String... segments);
}

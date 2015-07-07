package com.jive.myco.jazz.api.audit;


/**
 * Implementations of this are used to convert objects to a serialized form appropriate for the
 * targeted {@link AuditStorage}.
 *
 * @author David Valeri
 */
public interface AuditEventSerializer
{
  /**
   * Returns {@code true} if this serializer can produce the given destination media type.
   *
   * @param mediaType
   *          the media type to serialize the content into
   *
   * @param <U>
   *          type of the object that we serialize to
   */
  boolean canSerializeTo(final String mediaType);

  /**
   * Converts the passed in object to a serialized form appropriate for the given
   * {@link AuditStorage}.
   *
   * @param auditEvent
   *          the event to be serialized
   * @param mediaType
   *          the media type to serialize the content into
   *
   * @param <U>
   *          type of the object that we serialize to
   *
   * @return byte array to be announced into ISP
   */
  byte[] serialize(final AuditEvent auditEvent, final String mediaType);
}

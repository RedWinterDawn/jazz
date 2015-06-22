package com.jive.myco.jazz.api.audit;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import org.joda.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A simple audit event.
 * @author Binh Tran
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class SimpleAuditEvent implements AuditEvent
{

  /**
   * The audit event type/category of the data.
   */
  private String type;

  /**
   * The instant this audit event was created.
   */
  @JsonProperty("@timestamp")
  private Instant created;

  /**
   * The auditing component which generated this event.
   */
  private String component;

  /**
   * the name of this event.
   */
  private String name;

  /**
   * meta data provided outside the scope of the audit generator (e.g, MDC).
   */
  private Map<String, Object> meta;


  /**
   * Audit event data, which may be @Value based.
   *
   * This does not include metadata around the environment, e.g calling thread etc.
   *
   */
  private Map<String, Object> data;
}

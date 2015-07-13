package com.jive.myco.jazz.api.audit;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Wither;

import org.joda.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A simple audit event.
 *
 * @author Binh Tran
 */
@Wither
@Getter
public class SimpleAuditEvent implements AuditEvent
{
  private final String type;

  @JsonProperty("@timestamp")
  private final Instant created;

  private final String component;

  private final String name;

  private final Map<String, Object> meta;

  private final Map<String, Object> data;

  @Builder
  private SimpleAuditEvent(
      @NonNull final String type,
      @NonNull final Instant created,
      @NonNull final String component,
      @NonNull final String name,
      @NonNull final Map<String, Object> meta,
      @NonNull final Map<String, Object> data)
  {
    this.type = type;
    this.created = created;
    this.component = component;
    this.name = name;
    this.meta = meta;
    this.data = data;
  }

  public static SimpleAuditEventBuilder builder()
  {
    return new SimpleAuditEventBuilder();
  }

  public static SimpleAuditEventBuilder builder(final AuditEvent event)
  {
    return builder()
        .component(event.getComponent())
        .created(event.getCreated())
        .data(event.getData())
        .meta(event.getMeta())
        .name(event.getName())
        .type(event.getType());
  }

  public static final class SimpleAuditEventBuilder
  {
    private final Map<String, Object> meta = new HashMap<>();

    private final Map<String, Object> data = new HashMap<>();

    private Instant created = Instant.now();

    public SimpleAuditEventBuilder meta(final Map<? extends String, ? extends Object> meta)
    {
      this.meta.clear();
      return addMeta(meta);
    }

    public SimpleAuditEventBuilder addMeta(final Map<? extends String, ? extends Object> meta)
    {
      this.meta.putAll(meta);
      return this;
    }

    public <V extends Object> SimpleAuditEventBuilder addMeta(final String key, final V value)
    {
      this.meta.put(key, value);
      return this;
    }

    public SimpleAuditEventBuilder data(final Map<? extends String, ? extends Object> data)
    {
      this.data.clear();
      return addData(meta);
    }

    public SimpleAuditEventBuilder addData(final Map<? extends String, ? extends Object> data)
    {
      this.data.putAll(data);
      return this;
    }

    public <V extends Object> SimpleAuditEventBuilder addData(final String key, final V value)
    {
      this.data.put(key, value);
      return this;
    }
  }
}

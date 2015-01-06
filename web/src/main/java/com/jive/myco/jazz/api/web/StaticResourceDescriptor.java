package com.jive.myco.jazz.api.web;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Builder;

/**
 * Represents a location to lookup resources from and the relative URL pattern used to map requests
 * to the static resources. The {@link #getPath() path} may be a relative or absolute file system
 * path.
 *
 * @author David Valeri
 */
@Getter
public final class StaticResourceDescriptor
{
  private final AtomicInteger INSTANCE_COUNTER = new AtomicInteger();

  /**
   * A descriptive ID for this static resource.
   */
  private final String id;

  /**
   * The path, relative to the apps's context path, where the files in {@link #path} are exposed.
   */
  @NonNull
  private final String relativeUrlPath;

  /**
   * The location from which to lookup static resources.
   */
  @NonNull
  private final String path;


  public StaticResourceDescriptor(final String relativeUrlPath, final String path)
  {
    this(null, relativeUrlPath, path);
  }

  @Builder
  public StaticResourceDescriptor(final String id, final String relativeUrlPath, final String path)
  {
    this.id = id == null ? "static-" + INSTANCE_COUNTER.getAndIncrement() : id;

    this.relativeUrlPath = relativeUrlPath;
    this.path = path;
  }

}

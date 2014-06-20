package com.jive.myco.jazz.api.web;

import java.net.URL;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Builder;

/**
 * Represents a location to lookup resources from and the relative URL pattern used to map requests
 * to the static resources. The {@link #getUrl() URL} may use either the file: or jar: schemes.
 *
 * @author David Valeri
 */
@RequiredArgsConstructor
@Getter
@Builder
public final class StaticResourceDescriptor
{
  /**
   * The path, relative to the apps's context path, where the files in {@link #url} are exposed.
   */
  @NonNull
  private final String relativeUrlPath;

  /**
   * The location from which to lookup static resources.
   */
  @NonNull
  private final URL url;
}

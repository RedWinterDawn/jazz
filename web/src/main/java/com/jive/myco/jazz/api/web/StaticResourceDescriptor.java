package com.jive.myco.jazz.api.web;

import java.nio.file.Path;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Builder;

/**
 * Represents a file system location to serve and the relative URL pattern used to map requests to
 * the static resources.
 *
 * @author David Valeri
 */
@RequiredArgsConstructor
@Getter
@Builder
public final class StaticResourceDescriptor
{
  /**
   * The path, relative to the apps's context path, where the files in {@link #fileSystemPath} are
   * exposed.
   */
  @NonNull
  private final String relativeUrlPath;

  /**
   * The location on the file system where the static resources are loaded from.
   */
  @NonNull
  private final Path fileSystemPath;
}

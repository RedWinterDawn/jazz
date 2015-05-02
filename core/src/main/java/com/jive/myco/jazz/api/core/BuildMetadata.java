package com.jive.myco.jazz.api.core;

import java.util.Optional;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Provides information regarding the ultimate source of something in the context of version control
 * and the build server.
 *
 * @author David Valeri
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class BuildMetadata
{
  private final Optional<String> branch;

  private final Optional<String> commitId;

  @NonNull
  private final String version;

  @NonNull
  private final String userName;
}

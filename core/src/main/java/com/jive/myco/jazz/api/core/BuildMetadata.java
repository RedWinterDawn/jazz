package com.jive.myco.jazz.api.core;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Builder;

/**
 *
 * @author David Valeri
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class BuildMetadata
{
  @NonNull
  private final String branch;

  @NonNull
  private final String commitId;

  @NonNull
  private final String version;

  @NonNull
  private final String userName;
}

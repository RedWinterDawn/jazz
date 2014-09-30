package com.jive.myco.jazz.api.health.service.v1;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * A value object for reporting health status data via the Health Service v1 REST API.
 *
 * @author David Valeri
 */
@RequiredArgsConstructor
@Getter
public final class HealthReport
{
  @NonNull
  private final HealthReportStatus healthReportStatus;

  @NonNull
  final String healthCheckId;
}

package com.jive.myco.jazz.api.health.service.v1;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;

/**
 * A value object for representing available {@link HealthReport}s via the Health Service v1 REST
 * API.
 * 
 * @author David Valeri
 */
@Getter
public class HealthReports
{
  private final List<URL> reports;

  public HealthReports(@NonNull final List<URL> reports)
  {
    this.reports = new ArrayList<>(reports);
  }
}

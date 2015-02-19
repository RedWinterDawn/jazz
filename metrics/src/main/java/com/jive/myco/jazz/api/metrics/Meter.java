package com.jive.myco.jazz.api.metrics;

/**
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
public interface Meter
{
  /**
   * Mark a single occurrence of an event
   */
  void mark();

  /**
   * Mark a number of occurrences of an event.
   *
   * @param occurrences
   *          the number of events to record
   */
  void mark(long occurrences);
}

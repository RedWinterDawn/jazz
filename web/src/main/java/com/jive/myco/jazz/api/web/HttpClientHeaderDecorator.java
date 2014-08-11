package com.jive.myco.jazz.api.web;

import java.util.List;
import java.util.Map;

public interface HttpClientHeaderDecorator
{
  void decorate(final Map<String, List<String>> headers);
}

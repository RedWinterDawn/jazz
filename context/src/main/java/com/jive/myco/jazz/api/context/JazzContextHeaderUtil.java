package com.jive.myco.jazz.api.context;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Preconditions;

/**
 * A utility class for writing {@link JazzContextManager Jazz Context} properties to HTTP/SIP
 * headers.
 *
 * @author David Valeri
 */
public final class JazzContextHeaderUtil
{
  private static final String HEADER_PREFIX = "J-Ctx-";

  private static final Pattern CONTEXT_PROPERTY_KEY_PARSER = Pattern.compile(
      HEADER_PREFIX + "(" + JazzContextManager.KEY_PATTERN.pattern() + ")");

  /**
   * Legacy header for trace ID.
   */
  public static final String TRACE_ID_HEADER = "J-Trace-ID";

  /**
   * Legacy header for request ID.
   */
  public static final String REQUEST_ID_HEADER = "J-Request-ID";

  public static String toHeaderName(final String contextPropertyKey)
  {
    Preconditions.checkArgument(
        JazzContextManager.KEY_PATTERN.matcher(contextPropertyKey).matches(),
        "Cannot map context property key [%s] to header.",
        contextPropertyKey);

    switch (contextPropertyKey)
    {
      case JazzContextManager.JAZZ_TRACE_ID_KEY:
        return TRACE_ID_HEADER;
      case JazzContextManager.JAZZ_CONTEXT_ID_KEY:
        return REQUEST_ID_HEADER;
      default:
        return HEADER_PREFIX + contextPropertyKey;
    }
  }

  public static Optional<String> fromHeaderName(final String headerName)
  {
    final Matcher matcher = CONTEXT_PROPERTY_KEY_PARSER.matcher(headerName);

    if (matcher.matches())
    {
      return Optional.of(matcher.group(1));
    }
    else if (TRACE_ID_HEADER.equals(headerName))
    {
      return Optional.of(JazzContextManager.JAZZ_TRACE_ID_KEY);
    }
    // NOTE: Not dealing with REQUEST_ID_HEADER because it is not put on outgoing request messages
    else
    {
      return Optional.empty();
    }
  }

  public static boolean isContextHeader(final String headerName)
  {
    return headerName != null && headerName.startsWith(HEADER_PREFIX)
        || TRACE_ID_HEADER.equals(headerName) || REQUEST_ID_HEADER.equals(headerName);
  }
}

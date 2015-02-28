package com.jive.myco.jazz.api.registry;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jive.v5.jumpy.JumpyService;

/**
 * A utility class for operations related to a {@link JazzService} instance.
 *
 * @author David Valeri
 */
public final class JazzServiceUtil
{
  private static final Pattern JUMPY_RECORD_NAME_PATTERN = Pattern.compile(
      "(_[^.]+._[^.]+)?\\.?([^\\/]+)?(?:\\/[v]([0-9]+))?");

  private JazzServiceUtil()
  {
    // Hidden in utility class.
  }

  /**
   * Utility method to convert a {@link JumpyService} annotation to a {@link JazzService}
   * annotation.
   *
   * @param jumpyService
   *          the original annotation to convert
   *
   * @return a {@link JazzService} equivalent to the supplied Jumpy service
   */
  public static JazzService toJazzService(final JumpyService jumpyService)
  {
    final Matcher matcher = JUMPY_RECORD_NAME_PATTERN
        .matcher(jumpyService.value());

    String name;
    String version;
    String protocol;

    if (matcher.matches() && matcher.group(2) != null)
    {
      name = matcher.group(2);
    }
    else
    {
      throw new IllegalArgumentException(
          String.format(
              "JumpyService name, [%s], does not match allowed name pattern.",
              jumpyService.value()));
    }

    if (matcher.matches() && matcher.group(3) != null)
    {
      final int lowerBound = Integer.parseInt(matcher.group(3));

      version = String.format("[%s,%s)", lowerBound, lowerBound + 1);
    }
    else
    {
      version = "[1,2)";
    }

    if (matcher.matches() && matcher.group(1) != null)
    {
      protocol = matcher.group(1);
    }
    else
    {
      protocol = null;
    }

    return new JazzService()
    {
      @Override
      public Class<? extends Annotation> annotationType()
      {
        return JazzService.class;
      }

      @Override
      public String serviceInterfaceVersion()
      {
        return version;
      }

      @Override
      public String serviceInterfaceName()
      {
        return name;
      }

      @Override
      public String protocol()
      {
        return protocol;
      }
    };
  }
}

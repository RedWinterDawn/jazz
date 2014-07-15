package com.jive.myco.jazz.api.core.network;

import java.net.InetAddress;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Builder;

/**
 * Represents a named network available in the environment.
 *
 * @author David Valeri
 */
@Builder
@Value
public final class Network
{
  @NonNull
  private final NetworkId id;

  @NonNull
  private final Set<InetAddress> inetAddresses;

  private Network(@NonNull final NetworkId id, @NonNull final Set<InetAddress> inetAddresses)
  {
    this.id = id;
    this.inetAddresses = Collections.unmodifiableSet(new HashSet<>(inetAddresses));
  }

  public boolean hasAddress(@NonNull final InetAddress inetAddresss)
  {
    return inetAddresses.contains(inetAddresss);
  }

  public static final class NetworkBuilder
  {
    Set<InetAddress> inetAddresses = new HashSet<>();

    public NetworkBuilder addAddresses(final InetAddress inetAddress, final InetAddress... rest)
    {
      inetAddresses.add(inetAddress);

      if (rest != null)
      {
        for (final InetAddress address : rest)
        {
          inetAddresses.add(address);
        }
      }

      return this;
    }

    public NetworkBuilder addAddresses(final Iterable<? extends InetAddress> additionalInetAddresses)
    {
      for (final InetAddress address : additionalInetAddresses)
      {
        inetAddresses.add(address);
      }

      return this;
    }

    @SuppressWarnings("unused")
    private void inetAddresses()
    {
      // Hidden (Lombok)
    }
  }
}

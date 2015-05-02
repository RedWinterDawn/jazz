package com.jive.myco.jazz.api.core.network;

import java.net.InetAddress;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.NonNull;

/**
 * Represents a collection of named networks in the environment.
 *
 * @author David Valeri
 */
public class Networks
{
  private final Map<NetworkId, Network> networkIdMap;

  @Builder
  private Networks(@NonNull final Set<Network> networks)
  {
    networkIdMap = Collections.unmodifiableMap(
        networks
            .stream()
            .collect(
                Collectors.toMap(Network::getId, Function.identity())));
  }

  public Set<Network> getNetorks()
  {
    return new HashSet<>(networkIdMap.values());
  }

  public Network getNetwork(final NetworkId networkId)
  {
    return networkIdMap.get(networkId);
  }

  public Network getNetwork(@NonNull final String networkId)
  {
    return networkIdMap.get(NetworkId.valueOf(networkId));
  }

  public Network getNetwork(@NonNull final InetAddress inetAddress)
  {
    for (final Network network : networkIdMap.values())
    {
      if (network.hasAddress(inetAddress))
      {
        return network;
      }
    }

    return null;
  }

  @Override
  public String toString()
  {
    final StringBuilder builder = new StringBuilder();
    builder.append("Networks [networkIdMap=");
    builder.append(networkIdMap);
    builder.append("]");
    return builder.toString();
  }

  public static final class NetworksBuilder
  {
    private final Set<Network> networks = new HashSet<>();

    public NetworksBuilder addNetworks(@NonNull final Network network,
        final Network... rest)
    {
      networks.add(network);

      if (rest != null)
      {
        for (final Network nw : rest)
        {
          networks.add(nw);
        }
      }

      return this;
    }

    @SuppressWarnings("unused")
    private void networks()
    {
      // Hidden (Lombok)
    }
  }
}

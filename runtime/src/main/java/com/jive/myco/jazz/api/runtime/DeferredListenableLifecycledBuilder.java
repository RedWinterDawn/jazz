package com.jive.myco.jazz.api.runtime;

import java.util.function.Function;

import com.jive.myco.commons.lifecycle.ListenableLifecycled;

/**
 * An interface for components in the plug-in that support the deferred creation of
 * {@link ListenableLifecycled} instances.
 *
 * @author David Valeri
 */
public interface DeferredListenableLifecycledBuilder<T>
{
  Function<T, ListenableLifecycled> getLifecycledBuilderFunc();
}

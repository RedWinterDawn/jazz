package com.jive.myco.jazz.api.core;

import com.jive.myco.commons.concurrent.PnkyPromise;

public interface Binding<T>
{
  PnkyPromise<T> remove();
}

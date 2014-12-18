package com.jive.myco.jazz.api.rest;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author John Norton.
 * @author Binh Tran.
 */
//Return values that are not in the excluded list;
public interface RestrictedSupplier<T> extends Supplier<T>
{
  T getExcluding(List<T> excluded);
}

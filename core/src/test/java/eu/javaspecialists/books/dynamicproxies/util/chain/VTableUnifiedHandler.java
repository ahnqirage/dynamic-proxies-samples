/*
 * Copyright (C) 2020 Heinz Max Kabutz
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.  Heinz Max Kabutz
 * licenses this file to you under the Apache License, Version
 * 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the
 * License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.  See the License for the specific
 * language governing permissions and limitations under the
 * License.
 */

package eu.javaspecialists.books.dynamicproxies.util.chain;

import eu.javaspecialists.books.dynamicproxies.util.*;

import java.lang.reflect.*;
import java.util.stream.*;

// tag::listing[]
public class VTableUnifiedHandler
    extends UnifiedInvocationHandler<Method> {
  private Object receiver;
  public VTableUnifiedHandler(
      Object receiver, VTable vtable,
      ChainedInvocationHandler next) {
    super(vtable::lookup, Method::invoke, next);
    this.receiver = receiver;
  }

  @Override
  protected Object getReceiver(Object proxy) {
    return receiver;
  }
}
// end::listing[]
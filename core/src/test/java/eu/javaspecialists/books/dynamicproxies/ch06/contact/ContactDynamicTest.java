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

package eu.javaspecialists.books.dynamicproxies.ch06.contact;

import eu.javaspecialists.books.dynamicproxies.*;
import eu.javaspecialists.books.dynamicproxies.handlers.*;
import eu.javaspecialists.books.dynamicproxies.util.*;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

public class ContactDynamicTest extends ContactTest {
  @Test
  public void testDynamicComposite() {
    var reducers = Map.of(
        new MethodKey(Contact.class, "count"),
        new Reducer(0, (r1, r2) -> (int) r1 + (int) r2)
    );
    test(() -> Proxies.compose(Contact.class, reducers));
  }

  @Test
  public void testTypeSafetyOfAdd() {
    Contact contact = Proxies.compose(Contact.class);
    contact.add(new Person("heinz@email.com"));

    try {
      BaseComponent untyped = contact;
      untyped.add("Hello world"); // Should ClassCastException
      fail("Expected a ClassCastException");
    } catch (ClassCastException success) { }
  }
}
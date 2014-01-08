/*
 * Copyright 2014 Yann D'Isanto.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mytdev.predicates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A predicate composite.
 *
 * @author Yann D'Isanto
 * @param <T>
 */
public abstract class PredicateComposite<T> extends AbstractPredicate<T> {

    /**
     * The underlying predicates.
     */
    protected final Collection<Predicate<? super T>> predicates;

    /**
     * Creates a new instance of PredicateComposite with the specified
     * predicates. The predicates are put in a collection with the
     * following order:
     * <ul>
     * <li>p1</li>
     * <li>p2</li>
     * <li>others (in the same order as specified in the method call)</li>
     * </ul>
     *
     * @param p1 predicate 1.
     * @param p2 predicate 2.
     * @param others more predicates.
     */
    public PredicateComposite(Predicate<? super T> p1, Predicate<? super T> p2, Predicate<? super T>... others) {
        assertNotNull(p1, "p1");
        assertNotNull(p2, "p2");
        final List<Predicate<? super T>> list = new ArrayList<Predicate<? super T>>(Arrays.asList(others));
        list.add(0, p2);
        list.add(0, p1);
        predicates = Collections.unmodifiableCollection(list);
    }

}

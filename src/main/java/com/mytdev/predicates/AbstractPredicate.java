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

/**
 * An Abstract Predicate implementing the and/or/not methods.
 *
 * @author Yann D'Isanto
 * @param <T>
 */
public abstract class AbstractPredicate<T> implements Predicate<T> {

    @Override
    public final Predicate<T> and(Predicate<? super T> predicate, Predicate<? super T>... others) {
        return new AndPredicate<T>(this, predicate, others);
    }

    @Override
    public final Predicate<T> or(Predicate<? super T> predicate, Predicate<? super T>... others) {
        return new OrPredicate<T>(this, predicate, others);
    }

    @Override
    public final Predicate<T> not() {
        return new NotPredicate<T>(this);
    }

    // util method
    static void assertNotNull(Object object, String name) {
        if(object == null) {
            throw new NullPointerException(name + " is null");
        }
    }
}

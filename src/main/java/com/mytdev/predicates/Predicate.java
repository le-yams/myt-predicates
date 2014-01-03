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
 *
 * @author Yann D'Isanto
 * @param <T> the type of the objects handled by this predicate.
 */
public interface Predicate<T> {

    /**
     * Evaluates the specified candidate.
     *
     * @param candidate the candidate to evaluate.
     * @return true if the specified candidate matches this predicate.
     */
    boolean eval(T candidate);

    /**
     * Creates then returns a predicate which applies a logical AND between this
     * predicate and the specified others.
     *
     * @param predicate another predicate.
     * @param others more other predicates.
     * @return a new Predicate instance.
     */
    Predicate<T> and(Predicate<T> predicate, Predicate<T>... others);

    /**
     * Creates then returns a predicate which applies a logical OR between this
     * predicate and the specified others.
     *
     * @param predicate another predicate.
     * @param others more other predicates.
     * @return a new Predicate instance.
     */
    Predicate<T> or(Predicate<T> predicate, Predicate<T>... others);

    /**
     * Creates then return a predicate which apply a logical NOT to this
     * predicate.
     *
     * @return a new Predicate instance.
     */
    Predicate<T> not();
}

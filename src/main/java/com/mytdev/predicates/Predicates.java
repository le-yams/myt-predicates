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
import java.util.Collection;
import java.util.List;

/**
 * Utiliy class to use predicates.
 *
 * @author Yann D'Isanto
 */
public final class Predicates {

    /**
     * Counts the number of elements in the specified list which satisfy a
     * predicate.
     *
     * @param <T>
     * @param collection the collection to count elements from.
     * @param predicate the predicate used to test elements.
     * @return the number of elements satisfying the specified predicate.
     */
    public static <T> int count(Collection<T> collection, Predicate<T> predicate) {
        int result = 0;
        for (T item : collection) {
            if (predicate.eval(item)) {
                result++;
            }
        }
        return result;
    }

    /**
     * Drops longest prefix of elements that satisfy a predicate.
     *
     * @param <T>
     * @param list
     * @param predicate the predicate used to test elements.
     * @return the longest suffix of this list whose first element does not
     * satisfy the given predicate.
     */
    public static <T> List<T> dropWhile(List<T> list, Predicate<T> predicate) {
        return new ArrayList<T>(list.subList(
            prefixLength(list, predicate),
            list.size()));
    }

    /**
     * Tests whether a predicate holds for some of the elements of a collection.
     *
     * @param <T>
     * @param collection the collection to test.
     * @param predicate the predicate used to test elements.
     * @return true if the given predicate holds for some of the elements of the
     * specified list, false otherwise.
     */
    public static <T> boolean exists(Collection<T> collection, Predicate<T> predicate) {
        for (T item : collection) {
            if (predicate.eval(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Selects all elements of this list which satisfy a predicate.
     *
     * @param <T>
     * @param collection the collection to filter.
     * @param predicate the predicate used to test elements.
     * @return a new list consisting of all elements of the specified collection
     * that satisfy the given predicate p. The order of the elements is
     * preserved if applicable.
     */
    public static <T> List<T> filter(Collection<T> collection, Predicate<T> predicate) {
        final List<T> result = new ArrayList<T>();
        for (T item : collection) {
            if (predicate.eval(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Finds the first element of the specified list satisfying a predicate, if
     * any.
     *
     * @param <T>
     * @param collection the collection to search.
     * @param predicate the predicate used to test elements.
     * @return the first element that satisfies the predicate or null if none
     * exists.
     */
    public static <T> T find(Collection<T> collection, Predicate<T> predicate) {
        for (T item : collection) {
            if (predicate.eval(item)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Tests whether a predicate holds for all elements of the specified list.
     *
     * @param <T>
     * @param collection the collection to test.
     * @param predicate the predicate used to test elements.
     * @return true if the given predicate holds for all elements of the
     * specified list, false otherwise.
     */
    public static <T> boolean forall(Collection<T> collection, Predicate<T> predicate) {
        for (T item : collection) {
            if (!predicate.eval(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Finds index of first element satisfying some predicate.
     *
     * @param <T>
     * @param list
     * @param predicate the predicate used to test elements.
     * @return the index of the first element of the specified list that
     * satisfies the given predicate, or -1, if none exists.
     */
    public static <T> int indexWhere(List<T> list, Predicate<T> predicate) {
        for (int i = 0; i < list.size(); i++) {
            if (predicate.eval(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds index of last element satisfying some predicate.
     *
     * @param <T>
     * @param list
     * @param predicate the predicate used to test elements.
     * @return the index of the last element of the specified list that
     * satisfies the given predicate, or -1, if none exists.
     */
    public static <T> int lastIndexWhere(List<T> list, Predicate<T> predicate) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (predicate.eval(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Partitions the specified collection in two lists according to a predicate.
     *
     * @param <T>
     * @param collection the collection to partition.
     * @param predicate the predicate on which to partition.
     * @return a pair of lists: the first list consists of all elements that
     * satisfy the given predicate and the second list consists of all elements
     * that don't. The relative order of the elements in the resulting lists is
     * the same as in the original list.
     */
    public static <T> Pair<List<T>> partition(Collection<T> collection, Predicate<T> predicate) {
        final List<T> matchingList = new ArrayList<T>();
        final List<T> unmatchingList = new ArrayList<T>();
        for (T item : collection) {
            if (predicate.eval(item)) {
                matchingList.add(item);
            } else {
                unmatchingList.add(item);
            }
        }
        return new Pair<List<T>>(matchingList, unmatchingList);
    }

    /**
     * Returns the length of the longest prefix whose elements all satisfy some
     * predicate.
     *
     * @param <T>
     * @param list the list to compute the prefix length from.
     * @param predicate the predicate used to test elements.
     * @return the length of the longest prefix of the specified list such that
     * every element of the segment satisfies the predicate.
     */
    public static <T> int prefixLength(List<T> list, Predicate<T> predicate) {
        int result = 0;
        for (T item : list) {
            if (predicate.eval(item)) {
                result++;
            } else {
                break;
            }
        }
        return result;
    }

    /**
     * Splits the specified list into a prefix/suffix pair according to a
     * predicate.
     *
     * @param <T>
     * @param list the list to split.
     * @param predicate the predicate used to test elements.
     * @return a pair consisting of the longest prefix of this list whose
     * elements all satisfy p, and the rest of this list.
     */
    public static <T> Pair<List<T>> span(List<T> list, Predicate<T> predicate) {
        final int spanIndex = prefixLength(list, predicate);
        return new Pair<List<T>>(
            new ArrayList<T>(list.subList(0, spanIndex)),
            new ArrayList<T>(list.subList(spanIndex, list.size())));
    }

    /**
     * Takes longest prefix of elements that satisfy a predicate.
     *
     * @param <T>
     * @param list
     * @param predicate the predicate used to test elements.
     * @return the longest prefix of this list whose elements all satisfy the
     * given predicate.
     */
    public static <T> List<T> takeWhile(List<T> list, Predicate<T> predicate) {
        return new ArrayList<T>(list.subList(0, prefixLength(list, predicate)));
    }

    private Predicates() {
    }

    /**
     * A simple immutable pair implementation.
     *
     * @param <T> the pair element type.
     */
    public static final class Pair<T> {

        private final T $1;

        private final T $2;

        public Pair(T $1, T $2) {
            this.$1 = $1;
            this.$2 = $2;
        }

        public T _1() {
            return $1;
        }

        public T _2() {
            return $2;
        }
    }
}

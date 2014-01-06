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
     * @param list the list to drop the elements from.
     * @param predicate the predicate.
     */
    public static <T> void dropWhile(List<T> list, Predicate<T> predicate) {
        int dropCount = prefixLength(list, predicate);
        for (int i = 0; i < dropCount; i++) {
            list.remove(0);
        }
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
     * Drops the elements not satisfying the given predicate in the specified
     * collection.
     *
     * @param <T>
     * @param collection the collection to filter.
     * @param predicate the predicate used to test elements.
     */
    public static <T> void filter(Collection<T> collection, Predicate<T> predicate) {
        final List<T> toRemove = new ArrayList<T>();
        for (T item : collection) {
            if (!predicate.eval(item)) {
                toRemove.add(item);
            }
        }
        collection.removeAll(toRemove);
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
     * Partitions the specified list in two lists according to a predicate. The
     * elements that don't satisfy the predicate are removed from the specified
     * collection and returned in a different list.
     *
     * @param <T>
     * @param collection the collection to partition.
     * @param predicate the predicate on which to partition.
     * @return a list of all elements that not satisfy the predicate.
     */
    public static <T> List<T> partition(Collection<T> collection, Predicate<T> predicate) {
        final List<T> unmatchingList = new ArrayList<T>();
        for (T item : collection) {
            if (!predicate.eval(item)) {
                unmatchingList.add(item);
            }
        }
        collection.removeAll(unmatchingList);
        return unmatchingList;
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
     * predicate. the suffix is removed from the list and returned as a new one.
     *
     * @param <T>
     * @param list the list to split.
     * @param predicate the predicate used to test elements.
     * @return the suffix as a list.
     */
    public static <T> List<T> span(List<T> list, Predicate<T> predicate) {
        final int spanIndex = prefixLength(list, predicate);
        final List<T> list2 = list.subList(spanIndex, list.size() - 1);
        while (spanIndex < list.size()) {
            list.remove(spanIndex);
        }
        return list2;
    }

    /**
     * Drops elements of the specified list from the index of the first element
     * that doesn't satisfy the given predicate.
     *
     * @param <T>
     * @param list
     * @param predicate the predicate used to test elements.
     */
    public static <T> void takeWhile(List<T> list, Predicate<T> predicate) {
        int dropIndex = prefixLength(list, predicate);
        while (dropIndex < list.size()) {
            list.remove(dropIndex);
        }
    }

    private Predicates() {
    }
}

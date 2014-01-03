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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yann D'Isanto
 */
public final class PredicateCompositeTest {

    @Test
    public void testNullPredicate1FaileConstruction() {
        // Arrange
        Predicate predicate = null;
        boolean npeThrown = false;

        // Act
        try {
            predicate = new PredicateComposite(null, TruePredicate.get()) {
                public boolean eval(Object candidate) {
                    throw new UnsupportedOperationException("not needed for this test");
                }
            };
        } catch (NullPointerException ex) {
            npeThrown = true;
        }

        // Assert
        assertNull(predicate);
        assertTrue(npeThrown);
    }

    @Test
    public void testNullPredicate2FaileConstruction() {
        // Arrange
        Predicate predicate = null;
        boolean npeThrown = false;

        // Act
        try {
            predicate = new PredicateComposite(TruePredicate.get(), null) {
                public boolean eval(Object candidate) {
                    throw new UnsupportedOperationException("not needed for this test");
                }
            };
        } catch (NullPointerException ex) {
            npeThrown = true;
        }

        // Assert
        assertNull(predicate);
        assertTrue(npeThrown);
    }

    @Test
    public void testComposedPredicatesComesFromConstructorArgsAndWellOrdered() {
        // Arrange
        final Predicate[] expectedPredicates = {
            TestUtils.createPredicate(),
            TestUtils.createPredicate(),
            TestUtils.createPredicate(),
            TestUtils.createPredicate()
        };
        final Predicate composite = new PredicateComposite(
            expectedPredicates[0],
            expectedPredicates[1],
            expectedPredicates[2],
            expectedPredicates[3]) {

                public boolean eval(Object candidate) {
                    int index = 0;
                    for (Object predicate : predicates) {
                        assertSame(expectedPredicates[index++], predicate);
                    }
                    return true;
                }
            };

        // Act
        // Assert
        composite.eval("a candidate");
    }
}

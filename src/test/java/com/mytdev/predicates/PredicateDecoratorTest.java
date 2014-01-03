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
public final class PredicateDecoratorTest {

    @Test
    public void testNullDecoratedFaileConstruction() {
        // Arrange
        Predicate predicate = null;
        boolean npeThrown = false;

        // Act
        try {
            predicate = new PredicateDecorator(null) {
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
    public void testDecoratedPredicateComesFromConstructorArg() {
        // Arrange
        final Predicate expectedDecoratedPredicate = TestUtils.createPredicate();
        final Predicate decorator = new PredicateDecorator(expectedDecoratedPredicate) {
            
            public boolean eval(Object candidate) {
                assertSame(expectedDecoratedPredicate, decoratedPredicate);
                return true;
            }
        };
        
        // Act
        // Assert
        decorator.eval("a candidate");
    }
}

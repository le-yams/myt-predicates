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
import static org.mockito.Mockito.*;

/**
 *
 * @author Yann D'Isanto
 */
public class AndPredicateTest {

    private static final Predicate TRUE = TruePredicate.get();

    private static final Predicate FALSE = FalsePredicate.get();

    /**
     * Test of eval method, of class AndPredicate.
     */
    @Test
    public void testLogicalANDOperatorApplication() {
        // Arrange
        final Predicate[] table = {
            new AndPredicate(FALSE, FALSE, FALSE),
            new AndPredicate(FALSE, FALSE, TRUE),
            new AndPredicate(FALSE, TRUE, FALSE),
            new AndPredicate(FALSE, TRUE, TRUE),
            new AndPredicate(TRUE, FALSE, FALSE),
            new AndPredicate(TRUE, FALSE, TRUE),
            new AndPredicate(TRUE, TRUE, FALSE),
            new AndPredicate(TRUE, TRUE, TRUE)
        };
        final boolean[] resultTable = new boolean[table.length];
        
        // Act
        for (int i = 0; i < table.length; i++) {
            resultTable[i] = table[i].eval("a candidate");
        }

        // Assert
        assertEquals(false, resultTable[0]);
        assertEquals(false, resultTable[1]);
        assertEquals(false, resultTable[2]);
        assertEquals(false, resultTable[3]);
        assertEquals(false, resultTable[4]);
        assertEquals(false, resultTable[5]);
        assertEquals(false, resultTable[6]);
        assertEquals(true, resultTable[7]);
    }

    @Test
    public void testShortCircuitingBehavior() {
        // Arrange
        final Predicate p1 = mock(Predicate.class);
        final Predicate p2 = mock(Predicate.class);
        final Predicate p3 = mock(Predicate.class);
        final Predicate predicate = new AndPredicate(p1, p2, p3);
        Object candidate = "a candidate";
        when(p1.eval(any())).thenReturn(true);
        when(p2.eval(any())).thenReturn(false);
        when(p3.eval(any())).thenReturn(true);
        
        // Act
        predicate.eval(candidate);
        
        // Assert
        verify(p1).eval(candidate);
        verify(p2).eval(candidate);
        verify(p3, times(0)).eval(candidate);
    }
}

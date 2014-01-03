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
public class OrPredicateTest {

    private static final Predicate TRUE = TruePredicate.get();

    private static final Predicate FALSE = FalsePredicate.get();

    /**
     * Test of eval method, of class AndPredicate.
     */
    @Test
    public void testLogicalOROperatorApplication() {
        // Arrange
        final Predicate[] table = {
            new OrPredicate(FALSE, FALSE, FALSE),
            new OrPredicate(FALSE, FALSE, TRUE),
            new OrPredicate(FALSE, TRUE, FALSE),
            new OrPredicate(FALSE, TRUE, TRUE),
            new OrPredicate(TRUE, FALSE, FALSE),
            new OrPredicate(TRUE, FALSE, TRUE),
            new OrPredicate(TRUE, TRUE, FALSE),
            new OrPredicate(TRUE, TRUE, TRUE)
        };
        final boolean[] resultTable = new boolean[table.length];
        
        // Act
        for (int i = 0; i < table.length; i++) {
            resultTable[i] = table[i].eval("a candidate");
        }

        // Assert
        assertEquals(false, resultTable[0]);
        assertEquals(true, resultTable[1]);
        assertEquals(true, resultTable[2]);
        assertEquals(true, resultTable[3]);
        assertEquals(true, resultTable[4]);
        assertEquals(true, resultTable[5]);
        assertEquals(true, resultTable[6]);
        assertEquals(true, resultTable[7]);
    }

    @Test
    public void testShortCircuitingBehavior() {
        // Arrange
        final Predicate p1 = mock(Predicate.class);
        final Predicate p2 = mock(Predicate.class);
        final Predicate p3 = mock(Predicate.class);
        final Predicate predicate = new OrPredicate(p1, p2, p3);
        Object candidate = "a candidate";
        when(p1.eval(any())).thenReturn(false);
        when(p2.eval(any())).thenReturn(true);
        when(p3.eval(any())).thenReturn(false);
        
        // Act
        predicate.eval(candidate);
        
        // Assert
        verify(p1).eval(candidate);
        verify(p2).eval(candidate);
        verify(p3, times(0)).eval(candidate);
    }
}

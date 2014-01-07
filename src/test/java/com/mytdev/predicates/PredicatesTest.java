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

import com.mytdev.predicates.Predicates.Pair;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yann D'Isanto
 */
public class PredicatesTest {
   
    /**
     * Test of count method, of class Predicates.
     */
    @Test
    public void testCount() {
        // Arrange
        final List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        final Predicate<String> predicate = new AbstractPredicate<String>() {

            public boolean eval(String candidate) {
                return "c".compareTo(candidate) < 0;
            }
        };
        
        // Act
        final int count = Predicates.count(list, predicate);
        
        // Assert
        assertEquals(2, count);
    }

    /**
     * Test of dropWhile method, of class Predicates.
     */
    @Test
    public void testDropWhile() {
        // Arrange
        final List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        final Predicate<String> predicate = new AbstractPredicate<String>() {

            public boolean eval(String candidate) {
                return "c".compareTo(candidate) >= 0;
            }
        };
        
        // Act
        final List<String> result = Predicates.dropWhile(list, predicate);
        
        // Assert
        assertEquals(Arrays.asList("d", "e"), result);
    }

    /**
     * Test of exists method, of class Predicates.
     */
    @Test
    public void testExists() {
        // Arrange
        final List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        final Predicate<String> p1 = new AbstractPredicate<String>() {

            public boolean eval(String candidate) {
                return "c".equals(candidate);
            }
        };
        final Predicate<String> p2 = new AbstractPredicate<String>() {

            public boolean eval(String candidate) {
                return "z".equals(candidate);
            }
        };
        
        // Act
        final boolean b1 = Predicates.exists(list, p1);
        final boolean b2 = Predicates.exists(list, p2);
        
        // Assert
        assertTrue(b1);
        assertFalse(b2);
    }

    /**
     * Test of filter method, of class Predicates.
     */
    @Test
    public void testFilter() {
        // Arrange
        final List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        final Predicate<String> predicate = new AbstractPredicate<String>() {

            public boolean eval(String candidate) {
                return "b".equals(candidate) ||
                    "e".equals(candidate);
            }
        };
        
        // Act
        final List<String> result = Predicates.filter(list, predicate);
        
        // Assert
        assertEquals(Arrays.asList("b", "e"), result);
    }

    /**
     * Test of find method, of class Predicates.
     */
    @Test
    public void testFind() {
        // Arrange
        final List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        final Predicate<String> predicate = new AbstractPredicate<String>() {

            public boolean eval(String candidate) {
                return "c".compareTo(candidate) < 0;
            }
        };
        
        // Act
        final String result = Predicates.find(list, predicate);
        
        // Assert
        assertEquals("d", result);
    }

    /**
     * Test of forall method, of class Predicates.
     */
    @Test
    public void testForall() {
        // Arrange
        final List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        final Predicate<String> p1 = new AbstractPredicate<String>() {

            public boolean eval(String candidate) {
                return "e".compareTo(candidate) >= 0;
            }
        };
        final Predicate<String> p2 = new AbstractPredicate<String>() {

            public boolean eval(String candidate) {
                return "z".compareTo(candidate) < 0;
            }
        };
        
        // Act
        final boolean b1 = Predicates.forall(list, p1);
        final boolean b2 = Predicates.forall(list, p2);
        
        // Assert
        assertTrue(b1);
        assertFalse(b2);
    }

    /**
     * Test of indexWhere method, of class Predicates.
     */
    @Test
    public void testIndexWhere() {
        // Arrange
        final List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        final Predicate<String> predicate = new AbstractPredicate<String>() {

            public boolean eval(String candidate) {
                return "c".compareTo(candidate) < 0;
            }
        };
        
        // Act
        final int result = Predicates.indexWhere(list, predicate);
        
        // Assert
        assertEquals(3, result);
    }

    /**
     * Test of lastIndexWhere method, of class Predicates.
     */
    @Test
    public void testLastIndexWhere() {
        // Arrange
        final List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        final Predicate<String> predicate = new AbstractPredicate<String>() {

            public boolean eval(String candidate) {
                return "c".compareTo(candidate) < 0;
            }
        };
        
        // Act
        final int result = Predicates.lastIndexWhere(list, predicate);
        
        // Assert
        assertEquals(4, result);
    }

    /**
     * Test of partition method, of class Predicates.
     */
    @Test
    public void testPartition() {
        // Arrange
        final List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        final Predicate<String> predicate = new AbstractPredicate<String>() {

            public boolean eval(String candidate) {
                return "a".equals(candidate) || 
                    "c".compareTo(candidate) < 0;
            }
        };
        
        // Act
        final Pair<List<String>> result = Predicates.partition(list, predicate);
        
        // Assert
        assertEquals(Arrays.asList("a", "d", "e"), result._1());
        assertEquals(Arrays.asList("b", "c"), result._2());
    }

    /**
     * Test of prefixLength method, of class Predicates.
     */
    @Test
    public void testPrefixLength() {
    }

    /**
     * Test of span method, of class Predicates.
     */
    @Test
    public void testSpan() {
        // Arrange
        final List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        final Predicate<String> predicate = new AbstractPredicate<String>() {

            public boolean eval(String candidate) {
                return "c".compareTo(candidate) >= 0;
            }
        };
        
        // Act
        final Pair<List<String>> result = Predicates.span(list, predicate);
        
        // Assert
        assertEquals(Arrays.asList("a", "b", "c"), result._1());
        assertEquals(Arrays.asList("d", "e"), result._2());
    }

    /**
     * Test of takeWhile method, of class Predicates.
     */
    @Test
    public void testTakeWhile() {
        // Arrange
        final List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        final Predicate<String> predicate = new AbstractPredicate<String>() {

            public boolean eval(String candidate) {
                return "c".compareTo(candidate) >= 0;
            }
        };
        
        // Act
        final List<String> result = Predicates.takeWhile(list, predicate);
        
        // Assert
        assertEquals(Arrays.asList("a", "b", "c"), result);
    }
    
}

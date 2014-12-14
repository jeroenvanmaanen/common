package org.leialearns.common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class PairTest {

    @Test
    public void testGetters() {
        Pair pair = createPair("42", 37);
        assertEquals("42", pair.getLeft());
        assertEquals(37, pair.getRight());
    }

    @Test
    public void testEquals() {
        assertPairEquals(createPair(null, null), createPair(null, null));
        assertPairEquals(createPair(null, 42), createPair(null, 37 + 5));
        assertNotEquals(createPair(null, 42), createPair(null, 37));
        assertPairEquals(createPair("42", null), createPair("4" + "2", null));
        assertNotEquals(createPair("42", null), createPair("4", null));
        assertPairEquals(createPair("42", 42), createPair("4" + "2", 37 + 5));
        assertNotEquals(createPair("42", 42), createPair("4", 37 + 5));
        assertNotEquals(createPair("42", 42), createPair("4" + "2", 37));
    }

    @Test
    public void testCompareTo() {
        assertLessThan(createPair(null, null), createPair(null, -42));
        assertLessThan(createPair(null, 37), createPair(null, 42));
        assertLessThan(createPair(null, 42), createPair("", 42));
        assertLessThan(createPair("42", null), createPair("42", -37));
        assertLessThan(createPair("42", 37), createPair("42", 42));
    }

    public <C extends Comparable<C>> void assertLessThan(C left, C right) {
        assertTrue(Static.compare(left, right) < 0);
    }

    public <A extends Comparable<A>,B extends Comparable<B>> void assertPairEquals(Pair<A,B> left, Pair<A,B> right) {
        assertEquals(left, right);
        assertEquals(left.hashCode(), right.hashCode());
        assertEquals(0, left.compareTo(right));
    }

    public Pair<String,Integer> createPair(String left, Integer right) {
        return new Pair<>(left, right);
    }
}

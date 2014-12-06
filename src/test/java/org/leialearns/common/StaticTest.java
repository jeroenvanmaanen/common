package org.leialearns.common;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static java.lang.Boolean.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class StaticTest {

    @Test
    public void testNotNull() {
        Iterable<Integer> notNull = Static.notNull(null);
        assertNotNull(notNull);
        assertFalse(notNull.iterator().hasNext());

        notNull = Static.notNull(Arrays.asList(37, 42));
        assertNotNull(notNull);
        Iterator<Integer> iterator = notNull.iterator();
        assertEquals(Integer.valueOf(37), iterator.next());
        assertEquals(Integer.valueOf(42), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testAsList() {
        Set<Integer> set = new HashSet<>();
        set.add(37);
        set.add(42);
        assertFalse(set instanceof List);
        List<Integer> list = Static.asList(set);
        assertEquals(2, list.size());
        for (Integer item : list) {
            assertTrue(set.contains(item));
        }
    }

    @Test
    public void testCompare() {
        assertEquals(0, Static.compare(null, null));
        assertEquals(-1, sgn(Static.compare(null, -1)));
        assertEquals(1, sgn(Static.compare(-1, null)));
        assertEquals(-1, sgn(Static.compare(-1, 1)));
        assertEquals(1, sgn(Static.compare(1, -1)));
    }

    private int sgn(int i) {
        if (i < 0) {
            return -1;
        } else if (i > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Test
    public void testOffer() {
        Object[] mixed = Static.offer(TRUE, new Object[]{37, "Hi"});
        assertEquals(3, mixed.length);
        assertEquals(TRUE, mixed[0]);
        assertEquals(37, mixed[1]);
        assertEquals("Hi", mixed[2]);

        String[] strings = Static.offer(null, new String[]{"Hello", "World"});
        assertEquals(3, strings.length);
        assertNull(strings[0]);
        assertEquals("Hello", strings[1]);
        assertEquals("World", strings[2]);
    }

    @Test
    public void testOfferArray() {
        Object[] mixed = new Object[]{37, "Hi"};
        Object[] offered;

        offered = Static.offerArray(null, mixed);
        assertArrayEquals(mixed, offered);
        offered = Static.offerArray(mixed, null);
        assertArrayEquals(mixed, offered);

        offered = Static.offerArray(mixed, mixed);
        assertArrayEquals(new Object[] { 37, "Hi", 37, "Hi" }, offered);

        String[] strings = Static.offer(null, new String[]{"Hello", "World"});
        assertEquals(3, strings.length);
        assertNull(strings[0]);
        assertEquals("Hello", strings[1]);
        assertEquals("World", strings[2]);
    }

    @Test
    public void testEqual() {
        assertTrue(Static.equal(null, null));
        assertFalse(Static.equal(null, 0));
        assertFalse(Static.equal(0, null));
        assertTrue(Static.equal(0, 0));
        assertFalse(Static.equal(0, ""));
    }

    @Test
    public void testJoin() {
        assertEquals("a|b|c", Static.join("|", new String[] { "a", "b", "c" }));
        assertEquals("a, b, c", Static.join(", ", new String[] { "a", "b", "c" }));
    }

    @Test
    public void testToList() {
        Set<Integer> set = new HashSet<>();
        set.add(37);
        set.add(42);
        TypedIterable<Integer> typedIterable = new TypedIterable<>(set, Integer.class);
        List<Integer> list = Static.toList(typedIterable);
        assertEquals(set.size(), list.size());
        for (Integer i : list) {
            assertTrue(set.contains(i));
        }
    }

    @Test
    public void testGCD() {
        assertEquals(1, Static.gcd(13, 23));
        assertEquals(3, Static.gcd(15, 21));
    }
}

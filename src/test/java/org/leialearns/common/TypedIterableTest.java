package org.leialearns.common;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.Math.PI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class TypedIterableTest {

    @Test
    public void testTypeSafeTypedIterable() {
        Collection<Integer> integers = Arrays.asList(37, 42);
        TypedIterable<Integer> typedIterable = new TypedIterable<>(integers, Integer.class);
        assertSame(integers, typedIterable.getWrappedIterable());
        assertFalse(typedIterable.isEmpty());
        assertEquals(Integer.valueOf(37), typedIterable.first());
        Iterator<Integer> iterator = typedIterable.iterator();
        assertEquals(Integer.valueOf(37), iterator.next());
        assertEquals(Integer.valueOf(42), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testCastingTypedIterable() {
        Collection<Object> integers = Arrays.asList(37, 42);
        TypedIterable<Integer> typedIterable = new TypedIterable<>(Integer.class, integers);
        assertSame(integers, ((HasWrappedIterable) typedIterable).getWrappedIterable());
        Iterator<Integer> iterator = typedIterable.iterator();
        assertEquals(Integer.valueOf(37), iterator.next());
        assertEquals(Integer.valueOf(42), iterator.next());
        assertFalse(iterator.hasNext());
        try {
            new TypedIterable<>(Integer.class, Arrays.asList(PI));
            assertTrue("ClassCastException expected", false);
        } catch (ClassCastException exception) {
            assertTrue(true);
        }
    }

    @Test
    public void testStreamSupport() {
        TypedIterable<Integer> integers = new TypedIterable<>(Arrays.asList(37, 42), Integer.class);
        Stream<Integer> stream = integers.stream();
        assertNotNull(stream);
        Optional<Integer> result = stream.reduce((a, b) -> a == null ? b : a + b);
        assertEquals(Integer.valueOf(37 + 42), result.get());
    }

    @Test
    public void testEmptyTypedIterable() {
        TypedIterable<Integer> integers = new TypedIterable<>(Arrays.asList(), Integer.class);
        assertTrue(integers.isEmpty());
    }

}

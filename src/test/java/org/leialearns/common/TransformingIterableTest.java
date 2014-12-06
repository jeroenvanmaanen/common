package org.leialearns.common;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TransformingIterableTest {

    @Test
    public void testTransformingIterable() {
        Iterable<Integer> iterable = new TypedIterable<>(Arrays.asList(37, 42), Integer.class);
        TypedIterable<String> transformed = new TransformingIterable<>(iterable, String.class, Object::toString);
        Iterator<String> iterator = transformed.iterator();
        assertEquals("37", iterator.next());
        assertEquals("42", iterator.next());
        assertFalse(iterator.hasNext());
    }
}

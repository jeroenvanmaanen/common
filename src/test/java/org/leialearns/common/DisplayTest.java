package org.leialearns.common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.leialearns.common.Display.*;

public class DisplayTest {

    @Test
    public void testShow() {
        String show = show("!@#$%^&*()_+-={}[]:|;'\"\\<>?,./`~ \t\b\r\n\u0087\u2222");
        assertEquals("!@#$%^&*\\(\\)\\_+-=\\{\\}\\[\\]:|;\\'\\\"\\\\\\<\\>\\?,./`~_\\t\\x08\\r\\n\\x87\\u2222", show);
    }

    @Test
    public void testDisplayParts() {
        String display = displayParts("string", 15, new Value());
        assertEquals("[string|15|value]", display);
    }

    @Test
    public void testLiteral() {
        String part = "[ ]";
        String display = displayParts(part, L.literal(part));
        assertEquals("[\\[_\\]|[ ]]", display);
    }

    @Test
    public void testBaseSupplier() {
        Supplier<Integer> supplier = new BaseSupplier<Integer>() {
            @Override
            public Integer get() {
                return 37 + 5;
            }
        };
        String display = display(supplier);
        assertEquals("42", display);
    }

    @Test
    public void testAsDisplay() {
        Value value = new Value();
        Object display = asDisplay(value);
        assertFalse(String.class.isInstance(display));
        assertEquals("value", display.toString());
    }

    @Test
    public void testAsDisplayWithTypes() {
        Value value = new Value();
        Object displayWithTypes = asDisplayWithTypes(value);
        assertFalse(String.class.isInstance(displayWithTypes));
        assertEquals("<Value|<Interface>>value", displayWithTypes.toString());
    }

    @Test
    public void displayCompoundObject() throws Exception {
        Object compound = createCompound();
        assertEquals("{[37, 42], [..., null], <Class>, <Class>.forName(<String>) -> <Class>}", display(compound));
    }

    @Test
    public void displayCompoundObjectWithTypes() throws Exception {
        Object compound = createCompound();
        assertEquals("<ArrayList>{<long>[<Long<Number|<Serializable>>|<Comparable>>37, <Long<Number|<Serializable>>|<Comparable>>42], " +
                "[..., null], <Class|<Serializable><GenericDeclaration|<AnnotatedElement>><Type><AnnotatedElement>>, " +
                "<Class|<Serializable><GenericDeclaration|<AnnotatedElement>><Type><AnnotatedElement>>.forName(<String|<Serializable><Comparable><CharSequence>>) ->" +
                " <Class|<Serializable><GenericDeclaration|<AnnotatedElement>><Type><AnnotatedElement>>}",
                displayWithTypes(compound));
    }

    private Object createCompound() throws Exception {
        Collection<Object> collection = new ArrayList<>();
        collection.add(new long[] { 37, 42 });
        collection.add(new Object[] { collection, null });
        collection.add(Class.class);
        collection.add(Class.class.getMethod("forName", String.class));
        return collection;
    }

    private static class Value implements Interface {
        public String toString() {
            return "value";
        }
    }

    private interface Interface {
    }
}

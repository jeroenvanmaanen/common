package org.leialearns.common;

import org.junit.Test;

import java.io.IOException;

public class ExceptionWrapperTest {

    @Test(expected = RuntimeException.class)
    public void testExceptionWrapper() {
        throw ExceptionWrapper.wrap(new IOException("bla"));
    }
}

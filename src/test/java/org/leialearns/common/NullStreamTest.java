package org.leialearns.common;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class NullStreamTest {

    @Test
    public void testNullStream() throws IOException {
        InputStream nullStream = new NullStream();
        Assert.assertEquals(-1, nullStream.read());
    }
}

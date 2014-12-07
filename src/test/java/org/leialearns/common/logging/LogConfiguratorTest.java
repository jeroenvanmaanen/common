package org.leialearns.common.logging;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;

public class LogConfiguratorTest {

    @Test
    public void testLogConfigurator() throws Exception {
        String logDir = System.getProperty("java.io.tmpDir".toLowerCase());
        System.err.println(logDir);
        assertTrue(new File(logDir).isDirectory());
        LogConfigurator logConfigurator = new LogConfigurator(logDir);
        InputStream inputStream = this.getClass().getResourceAsStream("/logging.properties");
        logConfigurator.configure(inputStream);
        Logger log = LoggerFactory.getLogger(getClass());
        log.info("Logging configured");
        File logFile = new File(logDir, "test-common-0.log");
        assertTrue(logFile.exists());
        assertTrue(logFile.isFile());
    }
}

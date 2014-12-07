package org.leialearns.common.logging;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.LogManager;

/**
 * Configures the logging system.
 */
public class LogConfigurator {
    private final String logDir;

    /**
     * Creates a new <code>LogConfigurator</code> instance.
     * @param logDir The directory for log files
     * @throws IOException When the log directory cannot be created
     */
    public LogConfigurator(final String logDir)
    throws IOException {
        this.logDir = logDir;
        File logDirFile = new File(logDir);
        if (!logDirFile.exists() && !logDirFile.mkdirs()) {
            System.err.println("Failed to create directory: " + logDir);
        }
    }

    /**
     * Configures the logging system using the given logging properties. The logging properties resource is
     * filtered to replace '<code>LOG_DIR</code>' by the path of the directory where the log files must be placed.
     * @param loggingProperties The properties steam
     * @throws IOException When an exception occurs while reading the loggingProperties
     */
    public void configure(InputStream loggingProperties)
    throws IOException {
        System.err.println("Configuring logging using log directory: " + logDir);
        Reader logConfigReader = new InputStreamReader(loggingProperties);
        InputStream logConfigStream = new LineFilterInputStream(logConfigReader, line -> line.replaceAll("LOG_DIR", logDir));
        LogManager logManager = LogManager.getLogManager();
        logManager.readConfiguration(logConfigStream);
    }

}

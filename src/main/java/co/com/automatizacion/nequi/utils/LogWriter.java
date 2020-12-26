package co.com.automatizacion.nequi.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogWriter {

    private static Logger testLogger = LogManager.getLogger(LogWriter.class);
    private static Logger pageLogger = LogManager.getLogger(LogWriter.class);
    private static Logger dataBaseLogger = LogManager.getLogger(LogWriter.class);

    public static void testInfoLog(String message) {
        testLogger.info(message);
    }

    public static void testDebugLog(String message) {
        testLogger.debug(message);
    }

    public static void testWarningLog(String message) {
        testLogger.warn(message);
    }

    public static void testErrorLog(String message) {
        testLogger.error(message);
    }

    public static void testFatalLog(String message) {
        testLogger.fatal(message);
    }


    public static void dbInfoLog(String message) {
        dataBaseLogger.info(message);
    }

    public static void dbDebugLog(String message) {
        dataBaseLogger.debug(message);
    }

    public static void dbWarningLog(String message) {
        dataBaseLogger.warn(message);
    }

    public static void dbErrorLog(String message) {
        dataBaseLogger.error(message);
    }

    public static void dbFatalLog(String message) {
        dataBaseLogger.fatal(message);
    }


    public static void pageInfoLog(String message) {
        pageLogger.info(message);
    }

    public static void pageDebugLog(String message) {
        pageLogger.debug(message);
    }

    public static void pageWarningLog(String message) {
        pageLogger.warn(message);
    }

    public static void pageErrorLog(String message) {
        pageLogger.error(message);
    }

    public static void pageFatalLog(String message) {
        pageLogger.fatal(message);
    }
}

package logging;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class LoggingHandling {
    public static Logger logger = Logger.getLogger(LoggingHandling.class.getName());
    public static String logFileName;
    // public static final Logger logger =
    // Logger.getLogger(LoggingHandling.class.getName());
    private static FileHandler fh = null;
    static Logger globalLogger = Logger.getLogger("global");

    static {

        Handler[] handlers = globalLogger.getHandlers();
        for (Handler handler : handlers) {
            globalLogger.removeHandler(handler);
        }
        SimpleDateFormat format = new SimpleDateFormat("d-M-y  HH-mm-ss");
        try {
            fh = new FileHandler("Logs/" + format.format(Calendar.getInstance().getTime()) + ".log");
            LoggingHandling.logFileName= format.format(Calendar.getInstance().getTime()) + ".log";
        } catch (Exception e) {
            e.printStackTrace();
        }

        // fh.setFormatter(new SimpleFormatter());

        LoggingHandling.logger.addHandler(fh);
        LoggingHandling.logger.setUseParentHandlers(false);
        BriefLogFormatter.init();
    }

    public static void createNewLog() {
        Handler[] handlers = globalLogger.getHandlers();
        for (Handler handler : handlers) {
            globalLogger.removeHandler(handler);
        }
        SimpleDateFormat format = new SimpleDateFormat("d-M-y  HH-mm-ss");
        try {
            fh = new FileHandler("Logs/" + format.format(Calendar.getInstance().getTime()) + ".log");
            LoggingHandling.logFileName= format.format(Calendar.getInstance().getTime()) + ".log";
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoggingHandling.logger.addHandler(fh);
        LoggingHandling.logger.setUseParentHandlers(false);
        BriefLogFormatter.init();
    }

    public static void logError(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        logger.severe("Error: " + sw.toString());
        LoggingHandling.reportError(sw.toString());
    }

    public static void reportError(String e) {
        logger.severe(e);
    }

    public static void reportMailingError(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        logger.severe("Error: " + sw.toString());
    }

    public static void logError(FileNotFoundException e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        logger.severe("Error: " + sw.toString());
        LoggingHandling.reportError(sw.toString());
    }

}
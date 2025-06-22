import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;

public class Logger {
    public static Logger logger;
    private static Queue<LogModel> logs;
    private final Thread worker;
    private int bufferSize;
    private long flushBufferTime; //  In milliSeconds

    private Logger() {
        this.bufferSize = 200;
        this.flushBufferTime = 100L;
        logs = new ConcurrentLinkedQueue<>();
        worker = new Thread(this::process);
        worker.setDaemon(true);
        worker.start();
    }

    public static Logger getLogger() {
        if (logger == null) {
            synchronized (Logger.class) {
                if (logger == null) {
                    logger = new Logger();
                }
            }
        }
        return logger;
    }

    public void info(String log, Object... args) {
        addLog( log, LogEnum.INFO, args );
    }

    public void error(String log, Object... args) {
        addLog( log, LogEnum.ERROR, args );
    }

    public void debug(String log, Object... args) {
        addLog( log, LogEnum.DEBUG, args );
    }

    public void warn(String log, Object... args) {
        addLog( log, LogEnum.WARN, args );
    }

    private void addLog(String log, LogEnum logEnum, Object... args) {
        String logData = format(log, args);
        logs.add( new LogModel( logData, logEnum ) );
    }

    private String format(String message, Object... args) {
        for (Object arg : args) {
            message = message.replaceFirst("\\{}", Matcher.quoteReplacement(String.valueOf(arg)));
        }
        return message;
    }

    private void process() {
        List<LogModel> logBuffer = new ArrayList<>();
        long lastFlushTime = System.currentTimeMillis();
        while (true) {
            if (!logs.isEmpty()) {
                logBuffer.add( logs.poll() );
            }

            if (logBuffer.size() >= bufferSize || (System.currentTimeMillis() - lastFlushTime) >= flushBufferTime) {
                flush(logBuffer);
                logBuffer.clear();
                lastFlushTime = System.currentTimeMillis();
            }
        }
    }

    private void flush(List<LogModel> logs) {
        for (LogModel log: logs) {
            System.out.println( log.toString() );
        }
    }
}

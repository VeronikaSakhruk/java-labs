package parcelplus;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class Log {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static LocalTime clock = LocalTime.of(8, 0, 0);

    private Log() {
    }

    public static String fmt(double value) {
        return String.format(Locale.ROOT, "%.2f", value);
    }

    public static synchronized void log(String source, String event) {
        clock = clock.plusSeconds(1);
        System.out.println("[" + clock.format(FORMAT) + "] [" + source + "] " + event);
    }

    public static void work(String source, String event, int millis) {
        log(source, event);
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        synchronized (Log.class) {
            clock = clock.plusSeconds(millis / 100);
        }
    }
}

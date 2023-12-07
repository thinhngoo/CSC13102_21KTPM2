package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void log(String type, String message, String path) {
        String timestamp = dateFormat.format(new Date());
        System.out.println("[" + timestamp + "] [" + type.toUpperCase() + "] " + message + " (" + path + ")");
    }
}
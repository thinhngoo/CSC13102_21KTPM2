package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class File {
    public static boolean addLine(String filePath, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(line);
            writer.newLine();
            return true;
        } catch (IOException e) {
            Logger.log("error", e.getMessage(), "File.java");
            return false;
        }
    }
}

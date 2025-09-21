package iot.utils;

import java.io.FileWriter;
import java.io.IOException;

public class LoggerUtil {
    private final String filePath;
    private FileWriter writer;

    public LoggerUtil(String filePath) {
        this.filePath = filePath;
        try {
            writer = new FileWriter(filePath);
            writer.write("round,deviceId,trust,access\n"); // CSV header
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(int round, String deviceId, int trust, String access) {
        try {
            writer.write(round + "," + deviceId + "," + trust + "," + access + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (writer != null) writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

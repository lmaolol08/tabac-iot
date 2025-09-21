package iot.utils;

import java.io.FileWriter;
import java.io.IOException;

public class ResultLogger {
    private FileWriter writer;

    public ResultLogger(String filePath) {
        try {
            writer = new FileWriter(filePath);
            writer.write("devices,accuracy,precision,recall,timeMs\n"); // CSV header
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(int devices, double accuracy, double precision, double recall, long timeMs) {
        try {
            writer.write(devices + "," + accuracy + "," + precision + "," + recall + "," + timeMs + "\n");
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

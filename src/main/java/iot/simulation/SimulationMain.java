package iot.simulation;

import iot.devices.Device;
import iot.accesscontrol.AccessPolicy;
import iot.accesscontrol.TabacPolicy;
import iot.trust.TrustManager;
import iot.utils.ResultLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationMain {

    // ✅ Run one simulation with N devices
    public static Result runSimulation(int deviceCount, int rounds) {
        List<Device> devices = new ArrayList<>();
        Random random = new Random();

        // Create devices
        for (int i = 1; i <= deviceCount; i++) {
            boolean malicious = random.nextDouble() < 0.3; // 30% malicious
            devices.add(new Device(
                    "d" + i,
                    (i % 2 == 0 ? "sensor" : "actuator"),
                    (i % 3 == 0 ? "user" : "admin"),
                    "room-" + (i % 5),
                    random.nextInt(101),
                    malicious
            ));
        }

        TrustManager trustManager = new TrustManager();
        for (Device d : devices) trustManager.registerDevice(d);

        AccessPolicy tabac = new TabacPolicy(trustManager);

        // Metrics
        int TP = 0, TN = 0, FP = 0, FN = 0;

        // Timer start
        long start = System.currentTimeMillis();

        // Simulation rounds
        for (int round = 1; round <= rounds; round++) {
            for (Device d : devices) {
                boolean allowed = tabac.checkAccess(d, "light", "on");

                // Metrics
                if (!d.isMalicious() && allowed) TP++;
                else if (!d.isMalicious() && !allowed) FN++;
                else if (d.isMalicious() && allowed) FP++;
                else if (d.isMalicious() && !allowed) TN++;

                // Trust update
                if (d.isMalicious()) trustManager.updateTrust(d, -20);
                else trustManager.updateTrust(d, random.nextInt(10));
            }
        }

        // Timer end
        long end = System.currentTimeMillis();

        // Calculate metrics
        int total = TP + TN + FP + FN;
        double accuracy = (double)(TP + TN) / total;
        double precision = TP + FP == 0 ? 0 : (double)TP / (TP + FP);
        double recall = TP + FN == 0 ? 0 : (double)TP / (TP + FN);
        long time = end - start;

        return new Result(deviceCount, accuracy, precision, recall, time);
    }

    // ✅ Main method: run multiple experiments
    public static void main(String[] args) {
        int[] deviceSizes = {10, 100, 500, 1000};
        int rounds = 5;

        System.out.println("📊 Scalability Experiment Results");
        System.out.println("Devices\tAccuracy\tPrecision\tRecall\tTime(ms)");

        // ✅ set up CSV logger
        ResultLogger resultLogger = new ResultLogger("scalability_results.csv");

        for (int size : deviceSizes) {
            Result r = runSimulation(size, rounds);
            System.out.printf("%d\t%.2f\t\t%.2f\t\t%.2f\t%d%n",
                    r.devices, r.accuracy, r.precision, r.recall, r.timeMs);

                    // ✅ log to CSV
            resultLogger.log(r.devices, r.accuracy, r.precision, r.recall, r.timeMs);
        }

        // ✅ close CSV file
        resultLogger.close();
        System.out.println("\n✅ Results saved to scalability_results.csv");
    }

    // ✅ Small helper class for results
    static class Result {
        int devices;
        double accuracy, precision, recall;
        long timeMs;

        Result(int devices, double accuracy, double precision, double recall, long timeMs) {
            this.devices = devices;
            this.accuracy = accuracy;
            this.precision = precision;
            this.recall = recall;
            this.timeMs = timeMs;
        }
    }
}

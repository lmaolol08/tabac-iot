package iot.trust;

import iot.devices.Device;
import java.util.HashMap;
import java.util.Map;

public class TrustManager {
    private final Map<String, Integer> trustScores = new HashMap<>();
    private final int trustThreshold = 50; // below this → denied

    // Initialize trust
    public void registerDevice(Device device) {
        trustScores.put(device.getId(), device.getTrustScore());
    }

    // Update trust (positive or negative)
    public void updateTrust(Device device, int change) {
        int newScore = trustScores.getOrDefault(device.getId(), 100) + change;
        if (newScore < 0) newScore = 0;
        if (newScore > 100) newScore = 100;
        trustScores.put(device.getId(), newScore);
        device.setTrustScore(newScore);
    }

    // Check if device still trusted
    public boolean isTrusted(Device device) {
        return trustScores.getOrDefault(device.getId(), 100) >= trustThreshold;
    }
}

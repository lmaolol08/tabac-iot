package iot.devices;

/**
 * Represents an IoT device in the simulation.
 */
public class Device {
    private final String id;       // unique ID of the device
    private final String type;     // "camera", "sensor", "actuator"
    private final String owner;    // who owns the device
    private final String location; // physical location
    private int trustScore;        // trust score (changes during sim)
    private final boolean malicious; // true if device is attacker

    // Constructor
    public Device(String id, String type, String owner, String location, int trustScore, boolean malicious) {
        this.id = id;
        this.type = type;
        this.owner = owner;
        this.location = location;
        this.trustScore = trustScore;
        this.malicious = malicious;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getType() { return type; }
    public String getOwner() { return owner; }
    public String getLocation() { return location; }
    public int getTrustScore() { return trustScore; }
    public void setTrustScore(int trustScore) { this.trustScore = trustScore; }
    public boolean isMalicious() { return malicious; }

    // For quick printing
    @Override
    public String toString() {
        return String.format("Device[id=%s, type=%s, owner=%s, location=%s, trust=%d, malicious=%s]",
                id, type, owner, location, trustScore, malicious);
    }
}

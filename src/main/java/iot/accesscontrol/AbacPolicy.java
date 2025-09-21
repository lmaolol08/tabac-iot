package iot.accesscontrol;

import iot.devices.Device;

public class AbacPolicy implements AccessPolicy {

    @Override
    public boolean checkAccess(Device device, String resource, String action) {
        // Rule 1: Only admin can control actuators
        if (device.getType().equals("actuator") && !device.getOwner().equals("admin")) {
            return false;
        }

        // Rule 2: Sensors can only "read", not "write"
        if (device.getType().equals("sensor") && action.equals("write")) {
            return false;
        }

        // Default: allow
        return true;
    }
}

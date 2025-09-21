package iot.accesscontrol;

import iot.devices.Device;

public interface AccessPolicy {
    boolean checkAccess(Device device, String resource, String action);
}

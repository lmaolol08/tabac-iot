package iot.accesscontrol;

import iot.devices.Device;
import iot.trust.TrustManager;

public class TabacPolicy implements AccessPolicy {
    private final TrustManager trustManager;

    public TabacPolicy(TrustManager trustManager) {
        this.trustManager = trustManager;
    }

    @Override
    public boolean checkAccess(Device device, String resource, String action) {
        // Step 1: trust check
        if (!trustManager.isTrusted(device)) {
            return false;
        }

        // Step 2: fallback to ABAC rules
        AbacPolicy abac = new AbacPolicy();
        return abac.checkAccess(device, resource, action);
    }
}

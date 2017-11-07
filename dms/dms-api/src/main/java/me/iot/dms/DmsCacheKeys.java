package me.iot.dms;

import me.iot.common.usual.CacheKeys;

/**
 * Created by sylar on 16/5/25.
 */
public class DmsCacheKeys extends CacheKeys {

    public static String getCcsKeyForDasStatus(String nodeId) {
        return getDmsKey(CCS, "nodeStatus", nodeId);
    }

    public static String getCcsKeyForDeviceStatus(String deviceId) {
        return getDmsKey(CCS, "deviceStatus", deviceId);
    }

}

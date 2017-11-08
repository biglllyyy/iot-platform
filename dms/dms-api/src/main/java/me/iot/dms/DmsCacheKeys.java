package me.iot.dms;

import me.iot.common.usual.CacheKeys;

/**
 * @FileName             :  MqttConst
 * @Author                :  sylar
 * @CreateDate           :  2017/11/08
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @CopyRight             : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public class DmsCacheKeys extends CacheKeys {

    public static String getCcsKeyForDasStatus(String nodeId) {
        return getDmsKey(CCS, "nodeStatus", nodeId);
    }

    public static String getCcsKeyForDeviceStatus(String deviceId) {
        return getDmsKey(CCS, "deviceStatus", deviceId);
    }

}

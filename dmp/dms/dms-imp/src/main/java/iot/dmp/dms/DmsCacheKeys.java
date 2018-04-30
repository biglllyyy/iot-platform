package iot.dmp.dms;

import iot.common.consts.CacheKeys;

/**
 * @author :  sylar
 * @FileName :  DmsCacheKeys
 * @CreateDate :  2017/11/08
 * @Description :
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @CopyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public class DmsCacheKeys extends CacheKeys {

    public static String getKey_dcsStatus(String nodeId) {
        return generateKeyBySyscode("dms", "dcsStatus", nodeId);
    }

    public static String getKey_deviceStatus(String deviceId) {
        return generateKeyBySyscode("dms", "deviceStatus", deviceId);
    }

}

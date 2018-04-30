package iot.dmp.dms.po;

import iot.dmp.dms.dto.DeviceStatusDto;

/**
 * @author :  sylar
 * @FileName :
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
public class DeviceStatus extends DeviceStatusDto {
    public DeviceStatus() {
    }

    public DeviceStatus(String deviceId, String nodeId, String terminalIp, boolean connected) {
        this.deviceId = deviceId;
        this.nodeId = nodeId;
        this.terminalIp = terminalIp;
        this.connected = connected;
    }
}

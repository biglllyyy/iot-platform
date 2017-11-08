package me.iot.dms;

import me.iot.dms.entity.DeviceStatus;

import java.util.List;

/**
 * @author :  sylar
 * @FileName :  IDeviceStatusService
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
public interface IDeviceStatusService {
    /**
     * 查询设备状态
     *
     * @param deviceId
     * @return
     */
    DeviceStatus getDeviceStatus(String deviceId);

    /**
     * 批量查询设备状态
     *
     * @param deviceIds 设备编码数组
     * @return
     */
    List<DeviceStatus> getDeviceStatusBatch(String[] deviceIds);
}

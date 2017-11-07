package me.iot.dms;

import me.iot.dms.entity.DeviceStatus;

import java.util.List;

/**
 * Created by sylar on 16/5/25.
 */
public interface IDeviceStatusService {
    DeviceStatus getDeviceStatus(String deviceId);

    /**
     * 批量查询设备状态
     *
     * @param deviceIds 设备编码数组
     * @return
     */
    List<DeviceStatus> getDeviceStatusBatch(String[] deviceIds);
}

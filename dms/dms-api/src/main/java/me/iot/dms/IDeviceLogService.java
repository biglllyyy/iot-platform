package me.iot.dms;

import me.iot.dms.entity.DeviceLog;
import me.iot.common.dto.QueryResult;

/**
 * Created by sylar on 16/5/25.
 */
public interface IDeviceLogService {
    QueryResult<DeviceLog> getDeviceLogsByTime(String deviceId, String logType, long beginTime, long endTime, int pageIndex, int pageSize);

    QueryResult<DeviceLog> getDeviceLogsByTime(String deviceId, String deviceType, String logType, long beginTime, long endTime, int pageIndex, int pageSize);
}

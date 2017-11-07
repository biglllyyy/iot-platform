package me.iot.dms;

import me.iot.dms.entity.DeviceConnectionLog;
import me.iot.common.dto.QueryResult;

/**
 * Created by sylar on 16/5/25.
 */
public interface IDeviceConnectionLogService {
    QueryResult<DeviceConnectionLog> getDeviceConnectionLogsByDeviceId(String deviceId, long beginTime, long endTime, int pageIndex, int pageSize);
}

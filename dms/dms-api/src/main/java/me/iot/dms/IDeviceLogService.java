package me.iot.dms;

import me.iot.dms.entity.DeviceLog;
import me.iot.common.dto.QueryResult;
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
public interface IDeviceLogService {
    QueryResult<DeviceLog> getDeviceLogsByTime(String deviceId, String logType, long beginTime, long endTime, int pageIndex, int pageSize);

    QueryResult<DeviceLog> getDeviceLogsByTime(String deviceId, String deviceType, String logType, long beginTime, long endTime, int pageIndex, int pageSize);
}

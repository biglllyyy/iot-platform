package me.iot.dms;

import me.iot.common.dto.QueryResult;
import me.iot.dms.entity.DeviceLog;

/**
 * @author :  sylar
 * @FileName :  IDeviceLogService
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
public interface IDeviceLogService {
    /**
     * @param deviceId
     * @param logType
     * @param beginTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    QueryResult<DeviceLog> getDeviceLogsByTime(String deviceId, String logType, long beginTime, long endTime, int
            pageIndex, int pageSize);

    /**
     * @param deviceId
     * @param deviceType
     * @param logType
     * @param beginTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    QueryResult<DeviceLog> getDeviceLogsByTime(String deviceId, String deviceType, String logType, long beginTime,
                                               long endTime, int pageIndex, int pageSize);
}

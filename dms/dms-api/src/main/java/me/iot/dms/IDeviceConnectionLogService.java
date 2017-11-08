package me.iot.dms;

import me.iot.common.dto.QueryResult;
import me.iot.dms.entity.DeviceConnectionLog;

/**
 * @author :  sylar
 * @FileName :  IDeviceConnectionLogService
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
public interface IDeviceConnectionLogService {
    /**
     * @param deviceId
     * @param beginTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    QueryResult<DeviceConnectionLog> getDeviceConnectionLogsByDeviceId(String deviceId, long beginTime, long endTime,
                                                                       int pageIndex, int pageSize);
}

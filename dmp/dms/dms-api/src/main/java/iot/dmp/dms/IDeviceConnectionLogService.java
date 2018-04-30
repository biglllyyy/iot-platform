package iot.dmp.dms;

import iot.common.dto.QueryResult;
import iot.dmp.dms.dto.DeviceConnectionLogDto;

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
     * 根据设备id获取设备连接日志
     *
     * @param deviceId
     * @param beginTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    QueryResult<DeviceConnectionLogDto> getDeviceConnectionLogsByDeviceId(String deviceId, long beginTime, long endTime,
                                                                          int pageIndex, int pageSize);
}

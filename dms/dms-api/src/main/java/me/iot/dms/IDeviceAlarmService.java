package me.iot.dms;

import me.iot.common.dto.QueryResult;
import me.iot.dms.entity.DeviceAlarm;

import java.util.List;

/**
 * @author :  sylar
 * @FileName :  IDeviceAlarmService
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
public interface IDeviceAlarmService {
    /**
     * @param beginTime
     * @param endTime
     * @return
     */
    long countOfDeviceAlarm(long beginTime, long endTime);

    /**
     * @param deviceType
     * @param beginTime
     * @param endTime
     * @return
     */
    long countOfDeviceAlarmByDeviceType(String deviceType, long beginTime, long endTime);

    /**
     * @param deviceId
     * @param beginTime
     * @param endTime
     * @return
     */
    long countOfDeviceAlarmByDeviceId(String deviceId, long beginTime, long endTime);

    /**
     * @param deviceId
     * @param alarmCodes
     * @param beginTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    QueryResult<DeviceAlarm> getDeviceAlarmsByDeviceId(String deviceId, List<String> alarmCodes, long beginTime, long
            endTime, int pageIndex, int pageSize);
}

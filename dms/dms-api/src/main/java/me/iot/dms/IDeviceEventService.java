package me.iot.dms;

import me.iot.common.dto.QueryResult;
import me.iot.dms.entity.DeviceEvent;

import java.util.List;

/**
 * @author :  sylar
 * @FileName :  IDeviceEventService
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
public interface IDeviceEventService {

    /**
     * @param beginTime
     * @param endTime
     * @return
     */
    long countOfDeviceEvent(long beginTime, long endTime);

    /**
     * @param deviceType
     * @param beginTime
     * @param endTime
     * @return
     */
    long countOfDeviceEventByDeviceType(String deviceType, long beginTime, long endTime);

    /**
     * @param deviceId
     * @param beginTime
     * @param endTime
     * @return
     */
    long countOfDeviceEventByDeviceId(String deviceId, long beginTime, long endTime);

    /**
     * @param deviceId
     * @param eventCodes
     * @param beginTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    QueryResult<DeviceEvent> getDeviceEventsByDeviceId(String deviceId, List<String> eventCodes, long beginTime, long
            endTime, int pageIndex, int pageSize);
}

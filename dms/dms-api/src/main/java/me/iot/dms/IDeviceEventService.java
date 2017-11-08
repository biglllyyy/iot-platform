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
     * 设备事件数量
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    long countOfDeviceEvent(long beginTime, long endTime);

    /**
     * 根据设备类型获取事件数量
     *
     * @param deviceType
     * @param beginTime
     * @param endTime
     * @return
     */
    long countOfDeviceEventByDeviceType(String deviceType, long beginTime, long endTime);

    /**
     * 根据设备id获取设备事件数量
     *
     * @param deviceId
     * @param beginTime
     * @param endTime
     * @return
     */
    long countOfDeviceEventByDeviceId(String deviceId, long beginTime, long endTime);

    /**
     * 根据设备id获取设备事件信息
     *
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

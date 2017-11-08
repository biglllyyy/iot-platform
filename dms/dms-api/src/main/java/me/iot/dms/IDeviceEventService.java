package me.iot.dms;

import me.iot.dms.entity.DeviceEvent;
import me.iot.common.dto.QueryResult;

import java.util.List;

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
public interface IDeviceEventService {

    long countOfDeviceEvent(long beginTime, long endTime);

    long countOfDeviceEventByDeviceType(String deviceType, long beginTime, long endTime);

    long countOfDeviceEventByDeviceId(String deviceId, long beginTime, long endTime);

    QueryResult<DeviceEvent> getDeviceEventsByDeviceId(String deviceId, List<String> eventCodes, long beginTime, long endTime, int pageIndex, int pageSize);
}

package me.iot.dms;

import me.iot.dms.entity.DeviceAlarm;
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
public interface IDeviceAlarmService {
    long countOfDeviceAlarm(long beginTime, long endTime);

    long countOfDeviceAlarmByDeviceType(String deviceType, long beginTime, long endTime);

    long countOfDeviceAlarmByDeviceId(String deviceId, long beginTime, long endTime);

    QueryResult<DeviceAlarm> getDeviceAlarmsByDeviceId(String deviceId, List<String> alarmCodes, long beginTime, long endTime, int pageIndex, int pageSize);
}

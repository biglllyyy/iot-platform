package me.iot.dms.controller;

import me.iot.dms.service.DeviceEventServiceImpl;
import me.iot.common.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

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
@RestController
@RequestMapping("/dms")
public class DeviceEventController {

    @Autowired
    DeviceEventServiceImpl deviceEventServiceImpl;

    @RequestMapping(value = "/countOfDeviceEvent", method = RequestMethod.GET)
    public Result<?> countOfDeviceEvent(long beginTime, long endTime) {
        return Result.newSuccess(deviceEventServiceImpl.countOfDeviceEvent(beginTime, endTime));
    }

    @RequestMapping(value = "/countOfDeviceEventByDeviceType", method = RequestMethod.GET)
    public Result<?> countOfDeviceEventByDeviceType(String deviceType, long beginTime, long endTime) {
        return Result.newSuccess(deviceEventServiceImpl.countOfDeviceEventByDeviceType(deviceType, beginTime, endTime));
    }

    @RequestMapping(value = "/countOfDeviceEventByDeviceId", method = RequestMethod.GET)
    public Result<?> countOfDeviceEventByDeviceId(String deviceId, long beginTime, long endTime) {
        return Result.newSuccess(deviceEventServiceImpl.countOfDeviceEventByDeviceId(deviceId, beginTime, endTime));
    }

    @RequestMapping(value = "/getDeviceEventsByDeviceId", method = RequestMethod.GET)
    public Result<?> getDeviceEventsByDeviceId(String deviceId, String[] eventCodes, long beginTime, long endTime, int pageIndex, int pageSize) {
        return Result.newSuccess(deviceEventServiceImpl.getDeviceEventsByDeviceId(deviceId, Arrays.asList(eventCodes), beginTime, endTime, pageIndex, pageSize));
    }

}

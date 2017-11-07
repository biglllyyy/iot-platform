package me.iot.dms.controller;

import me.iot.dms.service.DeviceAlarmService;
import me.iot.common.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Created by sylar on 16/6/6.
 */
@RestController
@RequestMapping("/dms")
public class DeviceAlarmController {

    @Autowired
    DeviceAlarmService deviceAlarmService;

    @RequestMapping(value = "/countOfDeviceAlarm", method = RequestMethod.GET)
    public Result<?> countOfDeviceAlarm(long beginTime, long endTime) {
        return Result.newSuccess(deviceAlarmService.countOfDeviceAlarm(beginTime, endTime));
    }

    @RequestMapping(value = "/countOfDeviceAlarmByDeviceType", method = RequestMethod.GET)
    public Result<?> countOfDeviceAlarmByDeviceType(String deviceType, long beginTime, long endTime) {
        return Result.newSuccess(deviceAlarmService.countOfDeviceAlarmByDeviceType(deviceType, beginTime, endTime));
    }

    @RequestMapping(value = "/countOfDeviceAlarmByDeviceId", method = RequestMethod.GET)
    public Result<?> countOfDeviceAlarmByDeviceId(String deviceId, long beginTime, long endTime) {
        return Result.newSuccess(deviceAlarmService.countOfDeviceAlarmByDeviceId(deviceId, beginTime, endTime));
    }

    @RequestMapping(value = "/getDeviceAlarmsByDeviceId", method = RequestMethod.GET)
    public Result<?> getDeviceAlarmsByDeviceId(String deviceId, String[] alarmCodes, long beginTime, long endTime, int pageIndex, int pageSize) {
        return Result.newSuccess(deviceAlarmService.getDeviceAlarmsByDeviceId(deviceId, Arrays.asList(alarmCodes), beginTime, endTime, pageIndex, pageSize));
    }

}

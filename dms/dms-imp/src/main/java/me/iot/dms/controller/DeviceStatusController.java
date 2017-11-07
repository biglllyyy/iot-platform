package me.iot.dms.controller;

import me.iot.dms.service.DeviceStatusService;
import me.iot.common.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sylar on 16/6/6.
 */
@RestController
@RequestMapping("/dms")
public class DeviceStatusController {

    @Autowired
    DeviceStatusService deviceStatusService;

    @RequestMapping(value = "/getDeviceStatus", method = RequestMethod.GET)
    public Result<?> getDeviceStatus(String deviceId) {
        return Result.newSuccess(deviceStatusService.getDeviceStatus(deviceId));
    }

    @RequestMapping(value = "/getDeviceStatusBatch", method = RequestMethod.GET)
    public Result<?> getDeviceStatusBatch(String[] deviceIds) {
        return Result.newSuccess(deviceStatusService.getDeviceStatusBatch(deviceIds));
    }
}
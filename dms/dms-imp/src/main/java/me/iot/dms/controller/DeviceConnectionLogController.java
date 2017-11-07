package me.iot.dms.controller;

import me.iot.dms.service.DeviceConnectionLogService;
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
public class DeviceConnectionLogController {

    @Autowired
    DeviceConnectionLogService deviceConnectionLogService;

    @RequestMapping(value = "/getDeviceConnectionLogsByDeviceId", method = RequestMethod.GET)
    public Result<?> getDeviceConnectionLogsByDeviceId(String deviceId, long beginTime, long endTime, int pageIndex, int pageSize) {
        return Result.newSuccess(deviceConnectionLogService.getDeviceConnectionLogsByDeviceId(deviceId, beginTime, endTime, pageIndex, pageSize));
    }

}

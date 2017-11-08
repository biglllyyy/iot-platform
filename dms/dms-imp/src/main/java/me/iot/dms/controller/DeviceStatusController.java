package me.iot.dms.controller;

import me.iot.common.dto.Result;
import me.iot.dms.service.DeviceStatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :  sylar
 * @FileName :  MqttConst
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
@RestController
@RequestMapping("/dms")
public class DeviceStatusController {

    @Autowired
    DeviceStatusServiceImpl deviceStatusServiceImpl;

    @RequestMapping(value = "/getDeviceStatus", method = RequestMethod.GET)
    public Result<?> getDeviceStatus(String deviceId) {
        return Result.newSuccess(deviceStatusServiceImpl.getDeviceStatus(deviceId));
    }

    @RequestMapping(value = "/getDeviceStatusBatch", method = RequestMethod.GET)
    public Result<?> getDeviceStatusBatch(String[] deviceIds) {
        return Result.newSuccess(deviceStatusServiceImpl.getDeviceStatusBatch(deviceIds));
    }
}
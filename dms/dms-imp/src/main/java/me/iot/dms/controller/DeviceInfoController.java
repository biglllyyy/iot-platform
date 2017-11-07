package me.iot.dms.controller;

import me.iot.dms.service.DeviceInfoService;
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
public class DeviceInfoController {

    @Autowired
    DeviceInfoService deviceInfoService;

    @RequestMapping(value = "/countOfDeviceInfo", method = RequestMethod.GET)
    public Result<?> countOfDeviceInfo() {
        return Result.newSuccess(deviceInfoService.countOfDeviceInfo());
    }


    @RequestMapping(value = "/countOfDeviceInfoByDeviceType", method = RequestMethod.GET)
    public Result<?> countOfDeviceInfoByDeviceType(String deviceType) {
        return Result.newSuccess(deviceInfoService.countOfDeviceInfoByDeviceType(deviceType));
    }


    @RequestMapping(value = "/countOfDeviceInfoByDeviceTypeAndVersionCode", method = RequestMethod.GET)
    public Result<?> countOfDeviceInfoByDeviceTypeAndVersionCode(String deviceType, int versionCode) {
        return Result.newSuccess(deviceInfoService.countOfDeviceInfoByDeviceTypeAndVersionCode(deviceType, versionCode));
    }


    @RequestMapping(value = "/getDeviceInfoById", method = RequestMethod.GET)
    public Result<?> getDeviceInfoById(long id) {
        return Result.newSuccess(deviceInfoService.getDeviceInfoById(id));
    }


    @RequestMapping(value = "/getDeviceInfoByDeviceId", method = RequestMethod.GET)
    public Result<?> getDeviceInfoByDeviceId(String deviceId) {
        return Result.newSuccess(deviceInfoService.getDeviceInfoByDeviceId(deviceId));
    }


    @RequestMapping(value = "/getDeviceInfoByMac", method = RequestMethod.GET)
    public Result<?> getDeviceInfoByMac(String mac) {
        return Result.newSuccess(deviceInfoService.getDeviceInfoByMac(mac));
    }


    @RequestMapping(value = "/getDeviceInfosByDeviceType", method = RequestMethod.GET)
    public Result<?> getDeviceInfosByDeviceType(String deviceType, int pageIndex, int pageSize) {
        return Result.newSuccess(deviceInfoService.getDeviceInfosByDeviceType(deviceType, pageIndex, pageSize));
    }


    @RequestMapping(value = "/getDeviceInfosByDeviceTypeAndVersion", method = RequestMethod.GET)
    public Result<?> getDeviceInfosByDeviceTypeAndVersion(String deviceType, int versionCode, int pageIndex, int pageSize) {
        return Result.newSuccess(deviceInfoService.getDeviceInfosByDeviceTypeAndVersion(deviceType, versionCode, pageIndex, pageSize));
    }

}

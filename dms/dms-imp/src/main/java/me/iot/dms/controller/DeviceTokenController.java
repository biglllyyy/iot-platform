package me.iot.dms.controller;

import me.iot.dms.service.DeviceTokenService;
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
public class DeviceTokenController {

    @Autowired
    DeviceTokenService deviceTokenService;

    @RequestMapping(value = "/countOfDeviceToken", method = RequestMethod.GET)
    public Result<?> countOfDeviceToken() {
        return Result.newSuccess(deviceTokenService.countOfDeviceToken());
    }


    @RequestMapping(value = "/generateDeviceId", method = RequestMethod.GET)
    public Result<?> generateDeviceId(String deviceType, String token) {
        return Result.newSuccess(deviceTokenService.generateDeviceId(deviceType, token));
    }


    @RequestMapping(value = "/getDeviceTokenByDeviceId", method = RequestMethod.GET)
    public Result<?> getDeviceTokenByDeviceId(String deviceId) {
        return Result.newSuccess(deviceTokenService.getDeviceTokenByDeviceId(deviceId));
    }


    @RequestMapping(value = "/getDeviceTokenByToken", method = RequestMethod.GET)
    public Result<?> getDeviceTokenByToken(String token) {
        return Result.newSuccess(deviceTokenService.getDeviceTokenByToken(token));
    }


    @RequestMapping(value = "/getDeviceTokensByDeviceType", method = RequestMethod.GET)
    public Result<?> getDeviceTokensByDeviceType(String deviceType, int pageIndex, int pageSize) {
        return Result.newSuccess(deviceTokenService.getDeviceTokensByDeviceType(deviceType, pageIndex, pageSize));
    }
}

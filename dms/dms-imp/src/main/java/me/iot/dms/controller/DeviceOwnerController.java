package me.iot.dms.controller;

import com.google.common.collect.Lists;
import me.iot.dms.IDeviceInfoService;
import me.iot.dms.IDeviceOwnerService;
import me.iot.dms.entity.DeviceInfo;
import me.iot.common.dto.QueryResult;
import me.iot.common.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * File Name             :  DeviceOwnerController
 * Author                :  luhao
 * Create Date           :  2016/6/29
 * Description           :
 * Reviewed By           :
 * Reviewed On           :
 * Version History       :
 * Modified By           :
 * Modified Date         :
 * Comments              :
 * CopyRight             : COPYRIGHT(c) www.XXXXX.com   All Rights Reserved
 * *******************************************************************************************
 */
@RestController
@RequestMapping("/dms")
public class DeviceOwnerController {
    private Logger logger = LoggerFactory.getLogger(DeviceOwnerController.class);

    @Autowired
    private IDeviceOwnerService deviceOwnerService;
    @Autowired
    private IDeviceInfoService deviceInfoService;

    @RequestMapping(value = "/bindDevice", method = RequestMethod.POST)
    public Result<?> bindDevice(@RequestParam("ownerId") String ownerId, @RequestParam("deviceId") String deviceId) {
        try {
            deviceOwnerService.bindDevice(ownerId, deviceId);
        } catch (Exception e) {
            String errMsg = e.toString();
            logger.error(errMsg);
            return Result.newFaild(errMsg);
        }

        return Result.newSuccess();
    }

    @RequestMapping(value = "/bindDeviceList", method = RequestMethod.POST)
    public Result<?> bindDeviceList(@RequestBody Map<String, Object> paramMap) {
        String ownerId = (String) paramMap.get("ownerId");
        List<String> deviceIdList = (List<String>) paramMap.get("deviceIdList");
        try {
            deviceOwnerService.bindDevice(ownerId, deviceIdList);
        } catch (Exception e) {
            String errMsg = e.toString();
            logger.error(errMsg);
            return Result.newFaild(errMsg);
        }

        return Result.newSuccess();
    }

    @RequestMapping(value = "/unBindDevice", method = RequestMethod.POST)
    public Result<?> unbindDevice(@RequestParam("ownerId") String ownerId, @RequestParam(value = "deviceArray",
            required = false) String[] deviceArray) {

        try {
            if (deviceArray == null || deviceArray.length <= 0) {
                deviceOwnerService.unBindDevice(ownerId);
            } else {
                deviceOwnerService.unBindDevice(ownerId, Lists.newArrayList(deviceArray));
            }
        } catch (Exception e) {
            String errMsg = e.toString();
            logger.error(errMsg);
            return Result.newFaild(errMsg);
        }

        return Result.newSuccess();
    }

    @RequestMapping(value = "/getDeviceInfoByOwnerId", method = RequestMethod.GET)
    public Result<?> getDeviceInfoByOwnerId(@RequestParam("ownerId") String ownerId, @RequestParam("pageIndex") int
            pageIndex, @RequestParam("pageSize") int pageSize) {

        QueryResult<DeviceInfo> deviceInfoQueryResult = null;
        try {
            deviceInfoQueryResult = deviceInfoService.getDeviceInfoByOwnerId(ownerId, pageIndex, pageSize);
        } catch (Exception e) {
            String errMsg = e.toString();
            logger.error(errMsg);
            return Result.newFaild(errMsg);
        }

        return Result.newSuccess(deviceInfoQueryResult);
    }

    @RequestMapping(value = "/findDeviceByParams", method = RequestMethod.GET)
    public Result<?> findDeviceByParams(
            @RequestParam(value = "ownerIds", required = false) String[] ownerIds,
            @RequestParam(value = "deviceType", required = false) String deviceType,
            @RequestParam(value = "connected", required = false) boolean connected,
            @RequestParam("pageIndex") int pageIdnex,
            @RequestParam("pageSize") int pageSize) {

        QueryResult<DeviceInfo> result = null;
        try {
            result = deviceInfoService.findDeviceByParams(ownerIds, deviceType, connected, pageIdnex, pageSize);
        } catch (Exception e) {
            String errMsg = e.toString();
            logger.error(errMsg);
            return Result.newFaild(errMsg);
        }

        return Result.newSuccess(result);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result<?> deviceUpdate(List list) {

        String result = null;
        try {
            result = deviceOwnerService.deviceUpdate(list);
        } catch (Exception e) {
            String errMsg = e.toString();
            logger.error(errMsg);
            return Result.newFaild(errMsg);
        }

        return Result.newSuccess(result);
    }

}

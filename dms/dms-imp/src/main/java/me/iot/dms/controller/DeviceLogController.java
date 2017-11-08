package me.iot.dms.controller;

import me.iot.dms.service.DeviceLogServiceImpl;
import me.iot.common.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
/**
 * 日志管理页面控制器
 * Created by ft_abinge on 2016/7/2.
 */
@RestController
@RequestMapping("/dms")
public class DeviceLogController {
    @Autowired
    DeviceLogServiceImpl deviceLogServiceImpl;

    @RequestMapping(value = "/getDeviceLogsByTime", method = RequestMethod.GET)
    public Result<?> getDeviceLogsByTime(@RequestParam(required = false) String deviceId, @RequestParam(required = false) String deviceType, @RequestParam(required = false) String logType, long beginTime, long endTime, int pageIndex, int pageSize) {
        return Result.newSuccess(deviceLogServiceImpl.getDeviceLogsByTime(deviceId, deviceType, logType, beginTime, endTime, pageIndex, pageSize));
    }
}

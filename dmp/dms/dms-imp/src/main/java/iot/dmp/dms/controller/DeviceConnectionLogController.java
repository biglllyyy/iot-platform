package iot.dmp.dms.controller;

import iot.common.dto.Result;
import iot.dmp.dms.service.DeviceConnectionLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :  sylar
 * @FileName :  DeviceConnectionLogController
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
public class DeviceConnectionLogController {

    @Autowired
    DeviceConnectionLogServiceImpl deviceConnectionLogServiceImpl;

    @RequestMapping(value = "/getDeviceConnectionLogsByDeviceId", method = RequestMethod.GET)
    public Result<?> getDeviceConnectionLogsByDeviceId(String deviceId, long beginTime, long endTime, int pageIndex,
                                                       int pageSize) {
        return Result.newSuccess(deviceConnectionLogServiceImpl.getDeviceConnectionLogsByDeviceId(deviceId,
                beginTime, endTime, pageIndex, pageSize));
    }

}

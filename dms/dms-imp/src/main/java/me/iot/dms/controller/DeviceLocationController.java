package me.iot.dms.controller;

import com.google.common.base.Strings;
import me.iot.dms.dto.BindLocationParamsDto;
import me.iot.dms.service.DeviceLocationServiceImpl;
import me.iot.common.dto.Result;
import me.iot.util.gps.enums.CoorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RestController
@RequestMapping("/dms")
public class DeviceLocationController {
    @Autowired
    DeviceLocationServiceImpl deviceLocationServiceImpl;


    @RequestMapping(value = "/getLocation", method = RequestMethod.GET)
    public Result<?> getLocation(String deviceId, int coorType) {
        return Result.newSuccess(deviceLocationServiceImpl.getLocation(deviceId, coorType));
    }

    @RequestMapping(value = "/bindLocation", method = RequestMethod.POST)
    public Result<?> bindLocation(@RequestBody BindLocationParamsDto params) {

        if (Strings.isNullOrEmpty(params.getDeviceId())) {
            return Result.newFaild("无效的设备编码");
        }

        if (params.getLon() <= 0 || params.getLat() <= 0) {
            return Result.newFaild("无效的经纬度");
        }

        if (params.getCoorType() < CoorType.WGS84.getValue() || params.getCoorType() > CoorType.BD09LL.getValue()) {
            return Result.newFaild("无效的坐标类型");
        }

        long id = deviceLocationServiceImpl.bindLocation(params.getUserId(),
                params.getDeviceType(),
                params.getDeviceId(),
                params.getCoorType(),
                params.getLon(),
                params.getLat(),
                params.getLocationDesc());
        if (id > 0) {
            return Result.newSuccess();
        } else {
            return Result.newFaild();
        }
    }


}

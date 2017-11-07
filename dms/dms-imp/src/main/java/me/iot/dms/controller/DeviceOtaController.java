package me.iot.dms.controller;

import com.google.common.base.Strings;
import me.iot.dms.service.DeviceOtaService;
import me.iot.common.dto.QueryResult;
import me.iot.common.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by sylar on 16/6/6.
 */
@RestController
@RequestMapping("/dms")
public class DeviceOtaController {

    @Autowired
    DeviceOtaService deviceOtaService;


    @RequestMapping(value = "/getDeviceOtaFilesByDeviceType", method = RequestMethod.GET)
    public Result<?> getDeviceOtaFilesByDeviceType(String deviceType, int pageIndex, int pageSize) {
        return Result.newSuccess(deviceOtaService.getDeviceOtaFilesByDeviceType(deviceType, pageIndex, pageSize));
    }

    @RequestMapping(value = "/uploadOtaFile", method = RequestMethod.POST)
    public Result<?> uploadOtaFile(@RequestBody Map<String, Object> params) {

        String otaFullName = params.get("otaFullName") == null ? null : params.get("otaFullName").toString();
        String deviceType = params.get("deviceType") == null ? null : params.get("deviceType").toString();
        String strVersionCode = params.get("versionCode") == null ? null : params.get("versionCode").toString();
        String versionName = params.get("versionName") == null ? null : params.get("versionName").toString();
        String description = params.get("description") == null ? null : params.get("description").toString();
        String content = params.get("content") == null ? null : String.valueOf(params.get("content"));

        if (Strings.isNullOrEmpty(otaFullName)
                || Strings.isNullOrEmpty(deviceType)
                || Strings.isNullOrEmpty(strVersionCode)
                || Strings.isNullOrEmpty(versionName)
                || Strings.isNullOrEmpty(content)) {
            return Result.newFaild("参数不合法");
        }
        deviceOtaService.uploadOtaFile(otaFullName, deviceType, Integer.valueOf(strVersionCode), versionName, description, content);
        return Result.newSuccess();
    }

    @RequestMapping(value = "/findUpDocument", method = RequestMethod.GET)
    public Result<QueryResult<?>> findUpDocument(@RequestParam("deviceType") String deviceType, @RequestParam("connected") boolean connected,
                                                 @RequestParam("deviceCode") String deviceCode, @RequestParam("beginVersion") int beginVersion, @RequestParam("endVersion") int endVersion,
                                                 @RequestParam("pageIndex") int pageIdnex, @RequestParam("pageSize") int pageSize) {
        return Result.newSuccess(deviceOtaService.findUpDocument(deviceType, connected, deviceCode, beginVersion, endVersion, pageIdnex, pageSize));
    }
}

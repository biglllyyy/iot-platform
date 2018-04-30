package iot.dmp.dms.ui.controller;

import iot.common.dto.QueryResult;
import iot.common.dto.Result;
import iot.dmp.dms.dto.BindLocationParamsDto;
import iot.dmp.dms.ui.service.IDmsFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author :  sylar
 * @FileName :
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
public class DmsUiController {
    @Autowired
    IDmsFeignClient client;

    @RequestMapping(value = "/getDasConnectionLogsByNodeId", method = RequestMethod.GET)
    public Result<?> getDasConnectionLogsByNodeId(String nodeId, long beginTime, long endTime, int pageIndex, int
            pageSize) {
        return client.getDasConnectionLogsByNodeId(nodeId, beginTime, endTime, pageIndex, pageSize);
    }

    @RequestMapping(value = "/getDasStatus", method = RequestMethod.GET)
    public Result<?> getDasStatus(String nodeId) {
        return client.getDasStatus(nodeId);
    }

    @RequestMapping(value = "/countOfDeviceAlarm", method = RequestMethod.GET)
    public Result<?> countOfDeviceAlarm(long beginTime, long endTime) {
        return client.countOfDeviceAlarm(beginTime, endTime);
    }

    @RequestMapping(value = "/countOfDeviceAlarmByDeviceType", method = RequestMethod.GET)
    public Result<?> countOfDeviceAlarmByDeviceType(String deviceType, long beginTime, long endTime) {
        return client.countOfDeviceAlarmByDeviceType(deviceType, beginTime, endTime);
    }

    @RequestMapping(value = "/countOfDeviceAlarmByDeviceId", method = RequestMethod.GET)
    public Result<?> countOfDeviceAlarmByDeviceId(String deviceId, long beginTime, long endTime) {
        return client.countOfDeviceAlarmByDeviceId(deviceId, beginTime, endTime);
    }

    @RequestMapping(value = "/getDeviceAlarmsByDeviceId", method = RequestMethod.GET)
    public Result<?> getDeviceAlarmsByDeviceId(String deviceId, List<String> alarmCodes, long beginTime,
                                               long endTime, int pageIndex, int pageSize) {
        return client.getDeviceAlarmsByDeviceId(deviceId, alarmCodes, beginTime, endTime, pageIndex, pageSize);
    }

    @RequestMapping(value = "/getDeviceConnectionLogsByDeviceId", method = RequestMethod.GET)
    public Result<?> getDeviceConnectionLogsByDeviceId(String deviceId, long beginTime, long endTime, int pageIndex,
                                                       int pageSize) {
        return client.getDeviceConnectionLogsByDeviceId(deviceId, beginTime, endTime, pageIndex, pageSize);
    }

    @RequestMapping(value = "/countOfDeviceEvent", method = RequestMethod.GET)
    public Result<?> countOfDeviceEvent(long beginTime, long endTime) {
        return client.countOfDeviceEvent(beginTime, endTime);
    }

    @RequestMapping(value = "/countOfDeviceEventByDeviceType", method = RequestMethod.GET)
    public Result<?> countOfDeviceEventByDeviceType(String deviceType, long beginTime, long endTime) {
        return client.countOfDeviceEventByDeviceType(deviceType, beginTime, endTime);
    }

    @RequestMapping(value = "/countOfDeviceEventByDeviceId", method = RequestMethod.GET)
    public Result<?> countOfDeviceEventByDeviceId(String deviceId, long beginTime, long endTime) {
        return client.countOfDeviceEventByDeviceId(deviceId, beginTime, endTime);
    }

    @RequestMapping(value = "/getDeviceEventsByDeviceId", method = RequestMethod.GET)
    public Result<?> getDeviceEventsByDeviceId(String deviceId, List<String> eventCodes, long beginTime, long
            endTime, int pageIndex, int pageSize) {
        return client.getDeviceEventsByDeviceId(deviceId, eventCodes, beginTime, endTime, pageIndex, pageSize);
    }

    @RequestMapping(value = "/countOfDeviceInfo", method = RequestMethod.GET)
    public Result<?> countOfDeviceInfo() {
        return client.countOfDeviceInfo();
    }

    @RequestMapping(value = "/countOfDeviceInfoByDeviceType", method = RequestMethod.GET)
    public Result<?> countOfDeviceInfoByDeviceType(String deviceType) {
        return client.countOfDeviceInfoByDeviceType(deviceType);
    }

    @RequestMapping(value = "/countOfDeviceInfoByDeviceTypeAndVersionCode", method = RequestMethod.GET)
    public Result<?> countOfDeviceInfoByDeviceTypeAndVersionCode(String deviceType, int versionCode) {
        return client.countOfDeviceInfoByDeviceTypeAndVersionCode(deviceType, versionCode);
    }

    @RequestMapping(value = "/getDeviceInfoById", method = RequestMethod.GET)
    public Result<?> getDeviceInfoById(long id) {
        return client.getDeviceInfoById(id);
    }

    @RequestMapping(value = "/getDeviceInfoByDeviceId", method = RequestMethod.GET)
    public Result<?> getDeviceInfoByDeviceId(String deviceId) {
        return client.getDeviceInfoByDeviceId(deviceId);
    }

    @RequestMapping(value = "/getDeviceInfoByMac", method = RequestMethod.GET)
    public Result<?> getDeviceInfoByMac(String mac) {
        return client.getDeviceInfoByMac(mac);
    }

    @RequestMapping(value = "/getDeviceInfosByDeviceType", method = RequestMethod.GET)
    public Result<?> getDeviceInfosByDeviceType(String deviceType, int pageIndex, int pageSize) {
        return client.getDeviceInfosByDeviceType(deviceType, pageIndex, pageSize);
    }

    @RequestMapping(value = "/getDeviceInfosByDeviceTypeAndVersion", method = RequestMethod.GET)
    public Result<?> getDeviceInfosByDeviceTypeAndVersion(String deviceType, int versionCode, int pageIndex, int
            pageSize) {
        return client.getDeviceInfosByDeviceTypeAndVersion(deviceType, versionCode, pageIndex, pageSize);
    }

    @RequestMapping(value = "/getLocation", method = RequestMethod.GET)
    public Result<?> getLocation(String deviceId, int coorType) {
        return client.getLocation(deviceId, coorType);
    }

    @RequestMapping(value = "/bindLocation", method = RequestMethod.POST)
    public Result<?> bindLocation(@RequestBody BindLocationParamsDto params) {
        return client.bindLocation(params);
    }

    @RequestMapping(value = "/getDeviceLogsByTime", method = RequestMethod.GET)
    public Result<?> getDeviceLogsByTime(@RequestParam(required = false) String deviceId,
                                         @RequestParam(required = false) String deviceType,
                                         @RequestParam(required = false) String logType,
                                         long beginTime, long endTime, int pageIndex, int pageSize) {
        return client.getDeviceLogsByTime(deviceId, deviceType, logType, beginTime, endTime, pageIndex, pageSize);
    }

    @RequestMapping(value = "/getDeviceOtaFilesByDeviceType", method = RequestMethod.GET)
    public Result<?> getDeviceOtaFilesByDeviceType(String deviceType, int pageIndex, int pageSize) {
        return client.getDeviceOtaFilesByDeviceType(deviceType, pageIndex, pageSize);
    }

    @RequestMapping(value = "/uploadOtaFile", method = RequestMethod.POST)
    public Result<?> uploadOtaFile(@RequestBody Map<String, Object> params) {
        return client.uploadOtaFile(params);
    }

    @RequestMapping(value = "/findUpDocument", method = RequestMethod.GET)
    public Result<QueryResult<?>> findUpDocument(@RequestParam("deviceType") String deviceType,
                                                 @RequestParam("connected") boolean connected,
                                                 @RequestParam("deviceCode") String deviceCode,
                                                 @RequestParam("beginVersion") int beginVersion,
                                                 @RequestParam("endVersion") int endVersion,
                                                 @RequestParam("pageIndex") int pageIdnex,
                                                 @RequestParam("pageSize") int pageSize) {
        return client.findUpDocument(deviceType, connected, deviceCode, beginVersion, endVersion, pageIdnex, pageSize);
    }

    @RequestMapping(value = "/bindDevice", method = RequestMethod.POST)
    public Result<?> bindDevice(@RequestParam("ownerId") String ownerId, @RequestParam("deviceId") String deviceId) {
        return client.bindDevice(ownerId, deviceId);
    }

    @RequestMapping(value = "/bindDeviceList", method = RequestMethod.POST)
    public Result<?> bindDeviceList(@RequestBody Map<String, Object> paramMap) {
        return client.bindDeviceList(paramMap);
    }

    @RequestMapping(value = "/unBindDevice", method = RequestMethod.POST)
    public Result<?> unbindDevice(@RequestParam("ownerId") String ownerId, @RequestParam(value = "deviceArray",
            required = false) String[] deviceArray) {
        return client.unbindDevice(ownerId, deviceArray);
    }

    @RequestMapping(value = "/getDeviceInfoByOwnerId", method = RequestMethod.GET)
    public Result<?> getDeviceInfoByOwnerId(@RequestParam("ownerId") String ownerId,
                                            @RequestParam("pageIndex") int pageIndex,
                                            @RequestParam("pageSize") int pageSize) {
        return client.getDeviceInfoByOwnerId(ownerId, pageIndex, pageSize);
    }

    @RequestMapping(value = "/findDeviceByParams", method = RequestMethod.GET)
    public Result<?> findDeviceByParams(@RequestParam(value = "ownerIds", required = false) String[] ownerIds,
                                        @RequestParam(value = "deviceType", required = false) String deviceType,
                                        @RequestParam(value = "connected", required = false) boolean connected,
                                        @RequestParam("pageIndex") int pageIdnex,
                                        @RequestParam("pageSize") int pageSize) {
        return client.findDeviceByParams(ownerIds, deviceType, connected, pageIdnex, pageSize);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result<?> deviceUpdate(List list) {
        return client.deviceUpdate(list);
    }

    @RequestMapping(value = "/getDeviceStatus", method = RequestMethod.GET)
    public Result<?> getDeviceStatus(String deviceId) {
        return client.getDeviceStatus(deviceId);
    }

    @RequestMapping(value = "/getDeviceStatusBatch", method = RequestMethod.GET)
    public Result<?> getDeviceStatusBatch(String[] deviceIds) {
        return client.getDeviceStatusBatch(deviceIds);
    }

    @RequestMapping(value = "/countOfDeviceToken", method = RequestMethod.GET)
    public Result<?> countOfDeviceToken() {
        return client.countOfDeviceToken();
    }

    @RequestMapping(value = "/generateDeviceId", method = RequestMethod.GET)
    public Result<?> generateDeviceId(String deviceType, String token) {
        return client.generateDeviceId(deviceType, token);
    }

    @RequestMapping(value = "/getDeviceTokenByDeviceId", method = RequestMethod.GET)
    public Result<?> getDeviceTokenByDeviceId(String deviceId) {
        return client.getDeviceTokenByDeviceId(deviceId);
    }

    @RequestMapping(value = "/getDeviceTokenByToken", method = RequestMethod.GET)
    public Result<?> getDeviceTokenByToken(String token) {
        return client.getDeviceTokenByToken(token);
    }

    @RequestMapping(value = "/getDeviceTokensByDeviceType", method = RequestMethod.GET)
    public Result<?> getDeviceTokensByDeviceType(String deviceType, int pageIndex, int pageSize) {
        return client.getDeviceTokensByDeviceType(deviceType, pageIndex, pageSize);
    }
}

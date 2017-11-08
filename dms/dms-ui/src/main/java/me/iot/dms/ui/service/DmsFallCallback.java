package me.iot.dms.ui.service;

import me.iot.common.dto.QueryResult;
import me.iot.common.dto.Result;
import me.iot.dms.dto.BindLocationParamsDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
@Component
public class DmsFallCallback implements IDmsFeignClient {
    @Override
    public Result<?> getDasConnectionLogsByNodeId(String nodeId, long beginTime, long endTime, int pageIndex, int
            pageSize) {
        return null;
    }

    @Override
    public Result<?> getDasStatus(String nodeId) {
        return null;
    }

    @Override
    public Result<?> countOfDeviceAlarm(long beginTime, long endTime) {
        return null;
    }

    @Override
    public Result<?> countOfDeviceAlarmByDeviceType(String deviceType, long beginTime, long endTime) {
        return null;
    }

    @Override
    public Result<?> countOfDeviceAlarmByDeviceId(String deviceId, long beginTime, long endTime) {
        return null;
    }

    @Override
    public Result<?> getDeviceAlarmsByDeviceId(String deviceId, List<String> alarmCodes, long beginTime, long
            endTime, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public Result<?> getDeviceConnectionLogsByDeviceId(String deviceId, long beginTime, long endTime, int pageIndex,
                                                       int pageSize) {
        return null;
    }

    @Override
    public Result<?> countOfDeviceEvent(long beginTime, long endTime) {
        return null;
    }

    @Override
    public Result<?> countOfDeviceEventByDeviceType(String deviceType, long beginTime, long endTime) {
        return null;
    }

    @Override
    public Result<?> countOfDeviceEventByDeviceId(String deviceId, long beginTime, long endTime) {
        return null;
    }

    @Override
    public Result<?> getDeviceEventsByDeviceId(String deviceId, List<String> eventCodes, long beginTime, long
            endTime, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public Result<?> countOfDeviceInfo() {
        return null;
    }

    @Override
    public Result<?> countOfDeviceInfoByDeviceType(String deviceType) {
        return null;
    }

    @Override
    public Result<?> countOfDeviceInfoByDeviceTypeAndVersionCode(String deviceType, int versionCode) {
        return null;
    }

    @Override
    public Result<?> getDeviceInfoById(long id) {
        return null;
    }

    @Override
    public Result<?> getDeviceInfoByDeviceId(String deviceId) {
        return null;
    }

    @Override
    public Result<?> getDeviceInfoByMac(String mac) {
        return null;
    }

    @Override
    public Result<?> getDeviceInfosByDeviceType(String deviceType, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public Result<?> getDeviceInfosByDeviceTypeAndVersion(String deviceType, int versionCode, int pageIndex, int
            pageSize) {
        return null;
    }

    @Override
    public Result<?> getLocation(String deviceId, int coorType) {
        return null;
    }

    @Override
    public Result<?> bindLocation(BindLocationParamsDto params) {
        return null;
    }

    @Override
    public Result<?> getDeviceLogsByTime(String deviceId, String deviceType, String logType, long beginTime, long
            endTime, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public Result<?> getDeviceOtaFilesByDeviceType(String deviceType, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public Result<?> uploadOtaFile(Map<String, Object> params) {
        return null;
    }

    @Override
    public Result<QueryResult<?>> findUpDocument(String deviceType, boolean connected, String deviceCode, int
            beginVersion, int endVersion, int pageIdnex, int pageSize) {
        return null;
    }

    @Override
    public Result<?> bindDevice(String ownerId, String deviceId) {
        return null;
    }

    @Override
    public Result<?> bindDeviceList(Map<String, Object> paramMap) {
        return null;
    }

    @Override
    public Result<?> unbindDevice(String ownerId, String[] deviceArray) {
        return null;
    }

    @Override
    public Result<?> getDeviceInfoByOwnerId(String ownerId, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public Result<?> findDeviceByParams(String[] ownerIds, String deviceType, boolean connected, int pageIdnex, int
            pageSize) {
        return null;
    }

    @Override
    public Result<?> deviceUpdate(List list) {
        return null;
    }

    @Override
    public Result<?> getDeviceStatus(String deviceId) {
        return null;
    }

    @Override
    public Result<?> getDeviceStatusBatch(String[] deviceIds) {
        return null;
    }

    @Override
    public Result<?> countOfDeviceToken() {
        return null;
    }

    @Override
    public Result<?> generateDeviceId(String deviceType, String token) {
        return null;
    }

    @Override
    public Result<?> getDeviceTokenByDeviceId(String deviceId) {
        return null;
    }

    @Override
    public Result<?> getDeviceTokenByToken(String token) {
        return null;
    }

    @Override
    public Result<?> getDeviceTokensByDeviceType(String deviceType, int pageIndex, int pageSize) {
        return null;
    }
}

package me.iot.dms.service;

import me.iot.common.msg.*;
import me.iot.dms.IDeviceLocationService;
import me.iot.dms.IDeviceManageService;
import me.iot.dms.IDeviceOwnerService;
import me.iot.dms.dto.DeviceLocationDto;
import me.iot.dms.entity.*;
import me.iot.common.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sylar on 16/5/25.
 */

@Component("dms")
public class DeviceManageService implements IDmsMsgProcessor<IMsg>, IDeviceManageService {

    private static final Logger LOG = LoggerFactory.getLogger(DeviceManageService.class);

    @Autowired
    MsgLogService msgLogService;

    @Autowired
    DasConnectionLogService dasConnectionLogService;

    @Autowired
    DasStatusService dasStatusService;

    @Autowired
    DeviceAlarmService deviceAlarmService;

    @Autowired
    DeviceConnectionLogService deviceConnectionLogService;

    @Autowired
    DeviceDataService deviceDataService;

    @Autowired
    DeviceEventService deviceEventService;

    @Autowired
    DeviceInfoService deviceInfoService;

    @Autowired
    DeviceLogService deviceLogService;

    @Autowired
    DeviceMessageService deviceMessageService;

    @Autowired
    DeviceOtaService deviceOtaService;

    @Autowired
    DeviceStatusService deviceStatusService;

    @Autowired
    DeviceTokenService deviceTokenService;

    @Autowired
    IDeviceOwnerService deviceOwnerService;

    @Autowired
    IDeviceLocationService deviceLocationService;


    @Override
    public void processMsg(IMsg msg) {
        LOG.info("DMS process msg\n{}", msg);
        msgLogService.processMsg(msg);

        switch (msg.getMsgType()) {
            case Undefine:
                break;
            case DasConnection:
                dasConnectionLogService.processMsg((DasConnectionMsg) msg);
                break;
            case DeviceConnection:
                deviceConnectionLogService.processMsg((DeviceConnectionMsg) msg);
                break;
            case DeviceAlarm:
                deviceAlarmService.processMsg((DeviceAlarmMsg) msg);
                break;
            case DeviceData:
                deviceDataService.processMsg((DeviceDataMsg) msg);
                break;
            case DeviceEvent:
                deviceEventService.processMsg((DeviceEventMsg) msg);
                break;
            case DeviceInfo:
                deviceInfoService.processMsg((DeviceInfoMsg) msg);
                break;
            case DeviceLog:
                deviceLogService.processMsg((DeviceLogMsg) msg);
                break;
            case DeviceOta:
                //DeviceOtaMsg 是下发的,不用处理
                break;
            default:
                break;
        }

        deviceMessageService.processMsg(msg);
    }

    @Override
    public QueryResult<DasConnectionLog> getDasConnectionLogsByNodeId(String nodeId, long beginTime, long endTime, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public DasStatus getDasStatus(String nodeId) {
        return dasStatusService.getDasStatus(nodeId);
    }

    @Override
    public long countOfDeviceAlarm(long beginTime, long endTime) {
        return deviceAlarmService.countOfDeviceAlarm(beginTime, endTime);
    }

    @Override
    public long countOfDeviceAlarmByDeviceType(String deviceType, long beginTime, long endTime) {
        return deviceAlarmService.countOfDeviceAlarmByDeviceType(deviceType, beginTime, endTime);
    }

    @Override
    public long countOfDeviceAlarmByDeviceId(String deviceId, long beginTime, long endTime) {
        return deviceAlarmService.countOfDeviceAlarmByDeviceId(deviceId, beginTime, endTime);
    }

    @Override
    public QueryResult<DeviceAlarm> getDeviceAlarmsByDeviceId(String deviceId, List<String> alarmCodes, long beginTime, long endTime, int pageIndex, int pageSize) {
        return deviceAlarmService.getDeviceAlarmsByDeviceId(deviceId, alarmCodes, beginTime, endTime, pageIndex, pageSize);
    }

    @Override
    public QueryResult<DeviceConnectionLog> getDeviceConnectionLogsByDeviceId(String deviceId, long beginTime, long endTime, int pageIndex, int pageSize) {
        return deviceConnectionLogService.getDeviceConnectionLogsByDeviceId(deviceId, beginTime, endTime, pageIndex, pageSize);
    }

    @Override
    public long countOfDeviceEvent(long beginTime, long endTime) {
        return deviceEventService.countOfDeviceEvent(beginTime, endTime);
    }

    @Override
    public long countOfDeviceEventByDeviceType(String deviceType, long beginTime, long endTime) {
        return deviceEventService.countOfDeviceEventByDeviceType(deviceType, beginTime, endTime);
    }

    @Override
    public long countOfDeviceEventByDeviceId(String deviceId, long beginTime, long endTime) {
        return deviceEventService.countOfDeviceEventByDeviceId(deviceId, beginTime, endTime);
    }

    @Override
    public QueryResult<DeviceEvent> getDeviceEventsByDeviceId(String deviceId, List<String> eventCodes, long beginTime, long endTime, int pageIndex, int pageSize) {
        return deviceEventService.getDeviceEventsByDeviceId(deviceId, eventCodes, beginTime, endTime, pageIndex, pageSize);
    }

    @Override
    public long countOfDeviceInfo() {
        return deviceInfoService.countOfDeviceInfo();
    }

    @Override
    public long countOfDeviceInfoByDeviceType(String deviceType) {
        return deviceInfoService.countOfDeviceInfoByDeviceType(deviceType);
    }

    @Override
    public long countOfDeviceInfoByDeviceTypeAndVersionCode(String deviceType, int versionCode) {
        return deviceInfoService.countOfDeviceInfoByDeviceTypeAndVersionCode(deviceType, versionCode);
    }

    @Override
    public DeviceInfo getDeviceInfoById(long id) {
        return deviceInfoService.getDeviceInfoById(id);
    }

    @Override
    public DeviceInfo getDeviceInfoByDeviceId(String deviceId) {
        return deviceInfoService.getDeviceInfoByDeviceId(deviceId);
    }

    @Override
    public DeviceInfo getDeviceInfoByMac(String mac) {
        return deviceInfoService.getDeviceInfoByMac(mac);
    }

    @Override
    public QueryResult<DeviceInfo> getDeviceInfosByDeviceType(String deviceType, int pageIndex, int pageSize) {
        return deviceInfoService.getDeviceInfosByDeviceType(deviceType, pageIndex, pageSize);
    }

    @Override
    public QueryResult<DeviceInfo> getDeviceInfosByDeviceTypeAndVersion(String deviceType, int versionCode, int pageIndex, int pageSize) {
        return deviceInfoService.getDeviceInfosByDeviceTypeAndVersion(deviceType, versionCode, pageIndex, pageSize);
    }

    @Override
    public QueryResult<DeviceLog> getDeviceLogsByTime(String deviceId, String logType, long beginTime, long endTime, int pageIndex, int pageSize) {
        return deviceLogService.getDeviceLogsByTime(deviceId, logType, beginTime, endTime, pageIndex, pageSize);
    }

    @Override
    public QueryResult<DeviceLog> getDeviceLogsByTime(String deviceId, String deviceType, String logType, long beginTime, long endTime, int pageIndex, int pageSize) {
        return deviceLogService.getDeviceLogsByTime(deviceId, deviceType, logType, beginTime, endTime, pageIndex, pageSize);
    }

    @Override
    public void sendMsg(IMsg msg) {
        deviceMessageService.sendMsg(msg);
    }

//    @Override
//    public void subscribeMsgByDeviceTypes(Callback<IMsg> callback, List<String> deviceTypes) {
//        deviceMessageService.subscribeMsgByDeviceTypes(callback, deviceTypes);
//    }
//
//    @Override
//    public void subscribeMsgByDeviceIds(Callback<IMsg> callback, List<String> deviceIds) {
//        deviceMessageService.subscribeMsgByDeviceIds(callback, deviceIds);
//    }
//
//    @Override
//    public void unsubscribeMsg(Callback<IMsg> callback) {
//        deviceMessageService.unsubscribeMsg(callback);
//    }

    @Override
    public QueryResult<DeviceOtaFile> getDeviceOtaFilesByDeviceType(String deviceType, int pageIndex, int pageSize) {
        return deviceOtaService.getDeviceOtaFilesByDeviceType(deviceType, pageIndex, pageSize);
    }

    @Override
    public void uploadOtaFile(String otaFullName, String deviceType, int versionCode, String versionName, String description, String content) {
        deviceOtaService.uploadOtaFile(otaFullName, deviceType, versionCode, versionName, description, content);
    }

    /**
     * 根据参数查询设备升级文档信息
     *
     * @param deviceType   设备类型
     * @param connected    设备状态
     * @param deviceCode   设备编码
     * @param beginVersion 开始版本号
     * @param endVersion   结束版本号
     * @param pageIndex
     * @param pageSize     @return 设备升级文档信息
     */
    @Override
    public QueryResult<?> findUpDocument(String deviceType, boolean connected, String deviceCode, int beginVersion, int endVersion, int pageIndex, int pageSize) {
        return deviceOtaService.findUpDocument(deviceType, connected, deviceCode, beginVersion, endVersion, pageIndex, pageSize);
    }

    @Override
    public DeviceStatus getDeviceStatus(String deviceId) {
        return deviceStatusService.getDeviceStatus(deviceId);
    }

    @Override
    public List<DeviceStatus> getDeviceStatusBatch(String[] deviceIds) {
        return deviceStatusService.getDeviceStatusBatch(deviceIds);
    }

    @Override
    public long countOfDeviceToken() {
        return deviceTokenService.countOfDeviceToken();
    }

    @Override
    public String generateDeviceId(String deviceType, String token) {
        return deviceTokenService.generateDeviceId(deviceType, token);
    }

    @Override
    public DeviceToken getDeviceTokenByDeviceId(String deviceId) {
        return deviceTokenService.getDeviceTokenByDeviceId(deviceId);
    }

    @Override
    public DeviceToken getDeviceTokenByToken(String token) {
        return deviceTokenService.getDeviceTokenByToken(token);
    }

    @Override
    public QueryResult<DeviceToken> getDeviceTokensByDeviceType(String deviceType, int pageIndex, int pageSize) {
        return deviceTokenService.getDeviceTokensByDeviceType(deviceType, pageIndex, pageSize);
    }

    @Override
    public void bindDevice(String ownerId, String deviceId) {
        deviceOwnerService.bindDevice(ownerId, deviceId);
    }

    @Override
    public void bindDevice(String ownerId, List<String> deviceList) {
        deviceOwnerService.bindDevice(ownerId, deviceList);
    }

    @Override
    public void unBindDevice(String ownerId) {
        deviceOwnerService.unBindDevice(ownerId);
    }

    @Override
    public void unBindDevice(String ownerId, List<String> deviceList) {
        deviceOwnerService.unBindDevice(ownerId, deviceList);
    }

    @Override
    public QueryResult<DeviceInfo> getDeviceInfoByOwnerId(String ownerId, int pageIndex, int pageSize) {
        return deviceInfoService.getDeviceInfoByOwnerId(ownerId, pageIndex, pageSize);
    }

    @Override
    public String getOwnerIdByDeviceId(String deviceId) {
        return deviceOwnerService.getOwnerIdByDeviceId(deviceId);
    }

    @Override
    public QueryResult<DeviceInfo> findDeviceByParams(String[] ownerIds, String deviceType, boolean state, int pageIndex, int pageSize) {
        return deviceInfoService.findDeviceByParams(ownerIds, deviceType, state, pageIndex, pageSize);
    }

    @Override
    public String deviceUpdate(List list) {
        return deviceOwnerService.deviceUpdate(list);
    }

    @Override
    public QueryResult<MsgLog> getMsgLogs(String deviceType, String deviceId, String msgType, long beginTime, long endTime, int pageIndex, int pageSize) {
        return msgLogService.getMsgLogs(deviceType, deviceId, msgType, beginTime, endTime, pageIndex, pageSize);
    }

    @Override
    public long bindLocation(String userId, String deviceType, String deviceId, int coorType, double lon, double lat, String locationDesc) {
        return deviceLocationService.bindLocation(userId, deviceType, deviceId, coorType, lon, lat, locationDesc);
    }

    @Override
    public DeviceLocationDto getLocation(String deviceId, int coorType) {
        return deviceLocationService.getLocation(deviceId, coorType);
    }
}

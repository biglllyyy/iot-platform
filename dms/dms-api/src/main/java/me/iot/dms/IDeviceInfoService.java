package me.iot.dms;

import me.iot.dms.entity.DeviceInfo;
import me.iot.common.dto.QueryResult;

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
public interface IDeviceInfoService {

    long countOfDeviceInfo();

    long countOfDeviceInfoByDeviceType(String deviceType);

    long countOfDeviceInfoByDeviceTypeAndVersionCode(String deviceType, int versionCode);

    DeviceInfo getDeviceInfoById(long id);

    DeviceInfo getDeviceInfoByDeviceId(String deviceId);

    DeviceInfo getDeviceInfoByMac(String mac);

    QueryResult<DeviceInfo> getDeviceInfosByDeviceType(String deviceType, int pageIndex, int pageSize);

    QueryResult<DeviceInfo> getDeviceInfosByDeviceTypeAndVersion(String deviceType, int versionCode, int pageIndex, int pageSize);

    /**
     * 根据ownerId获取设备列表（分页）
     *
     * @param ownerId   拥有者id
     * @param pageIndex 当前页数
     * @param pageSize  每页显示条数
     * @return 设备列表
     */
    QueryResult<DeviceInfo> getDeviceInfoByOwnerId(String ownerId, int pageIndex, int pageSize);


    /**
     * 根据参数查询设备信息
     *
     * @param deviceType 设备类型
     * @param connected  是否在线
     * @param pageIndex  当前页数
     * @param pageSize   每页显示条数
     * @return 设备信息列表
     */
    QueryResult<DeviceInfo> findDeviceByParams(String[] ownerIds, String deviceType, boolean connected, int pageIndex, int pageSize);


}

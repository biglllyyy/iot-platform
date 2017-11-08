package me.iot.dms;

import me.iot.common.dto.QueryResult;
import me.iot.dms.entity.DeviceInfo;

/**
 * @author :  sylar
 * @FileName :  IDeviceInfoService
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
public interface IDeviceInfoService {

    /**
     * 设备信息数量
     *
     * @return
     */
    long countOfDeviceInfo();

    /**
     * 同一设备类型的设备信息数量
     *
     * @param deviceType
     * @return
     */
    long countOfDeviceInfoByDeviceType(String deviceType);

    /**
     * 同一设备类型和版本相关的设备信息
     *
     * @param deviceType
     * @param versionCode
     * @return
     */
    long countOfDeviceInfoByDeviceTypeAndVersionCode(String deviceType, int versionCode);

    /**
     * 根据id获取设备信息
     *
     * @param id
     * @return
     */
    DeviceInfo getDeviceInfoById(long id);

    /**
     * 根据设备id获取设备信息
     *
     * @param deviceId
     * @return
     */
    DeviceInfo getDeviceInfoByDeviceId(String deviceId);

    /**
     * 根据mac获取设备信息
     *
     * @param mac
     * @return
     */
    DeviceInfo getDeviceInfoByMac(String mac);

    /**
     * 根据设备类型获取设备信息
     *
     * @param deviceType
     * @param pageIndex
     * @param pageSize
     * @return
     */
    QueryResult<DeviceInfo> getDeviceInfosByDeviceType(String deviceType, int pageIndex, int pageSize);

    /**
     * 根据设备类型和版本获取设备信息
     *
     * @param deviceType
     * @param versionCode
     * @param pageIndex
     * @param pageSize
     * @return
     */
    QueryResult<DeviceInfo> getDeviceInfosByDeviceTypeAndVersion(String deviceType, int versionCode, int pageIndex,
                                                                 int pageSize);

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
     * @param ownerIds   owner id数组
     * @param deviceType 设备类型
     * @param connected  是否在线
     * @param pageIndex  当前页数
     * @param pageSize   每页显示条数
     * @return 设备信息列表
     */
    QueryResult<DeviceInfo> findDeviceByParams(String[] ownerIds, String deviceType, boolean connected, int
            pageIndex, int pageSize);


}

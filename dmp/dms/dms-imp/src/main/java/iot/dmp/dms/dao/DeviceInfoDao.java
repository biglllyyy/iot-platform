package iot.dmp.dms.dao;

import iot.dmp.dms.po.DeviceInfo;
import iot.util.data.jpa.BaseJpaRepository;

/**
 * @author :  sylar
 * @FileName :  DeviceInfoDao
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
public interface DeviceInfoDao extends BaseJpaRepository<DeviceInfo, Long> {

    /**
     * 根据设备id获取设备信息
     *
     * @param deviceId
     * @return
     */
    DeviceInfo getByDeviceId(String deviceId);

    /**
     * 根据设备mac获取设备信息
     *
     * @param mac
     * @return
     */
    DeviceInfo getByMac(String mac);

    /**
     * bid获取设备信息
     *
     * @param bid
     * @return
     */
    DeviceInfo getByBid(String bid);
}

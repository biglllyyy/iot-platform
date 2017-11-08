package me.iot.dms.dao;

import me.iot.dms.entity.DeviceInfo;
import me.iot.util.jpa.BaseRepository;

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
public interface DeviceInfoDao extends BaseRepository<DeviceInfo, Long> {

    /**
     * @param deviceId
     * @return
     */
    DeviceInfo getByDeviceId(String deviceId);

    /**
     * @param mac
     * @return
     */
    DeviceInfo getByMac(String mac);

    /**
     * @param bid
     * @return
     */
    DeviceInfo getByBid(String bid);
}

package me.iot.dms.dao;

import me.iot.dms.entity.DeviceLocation;
import me.iot.util.jpa.BaseRepository;

/**
 * @author :  sylar
 * @FileName :  DeviceLocationDao
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
public interface DeviceLocationDao extends BaseRepository<DeviceLocation, Long> {

    /**
     * @param deviceId
     * @return
     */
    DeviceLocation getByDeviceId(String deviceId);
}

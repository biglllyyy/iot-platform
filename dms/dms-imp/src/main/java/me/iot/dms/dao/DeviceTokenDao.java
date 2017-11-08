package me.iot.dms.dao;

import me.iot.dms.entity.DeviceToken;
import me.iot.util.jpa.BaseRepository;

/**
 * @author :  sylar
 * @FileName :  DeviceTokenDao
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
public interface DeviceTokenDao extends BaseRepository<DeviceToken, Long> {

    /**
     * 根据设备id获取设备token信息
     *
     * @param deviceId
     * @return
     */
    DeviceToken getByDeviceId(String deviceId);

    /**
     * 根据token获取设备token信息
     *
     * @param token
     * @return
     */
    DeviceToken getByToken(String token);
}

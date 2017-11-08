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
     * @param deviceId
     * @return
     */
    DeviceToken getByDeviceId(String deviceId);

    /**
     * @param token
     * @return
     */
    DeviceToken getByToken(String token);
}

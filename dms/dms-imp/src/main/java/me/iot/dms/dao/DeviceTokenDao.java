package me.iot.dms.dao;

import me.iot.dms.entity.DeviceToken;
import me.iot.util.jpa.BaseRepository;

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
public interface DeviceTokenDao extends BaseRepository<DeviceToken, Long> {

    DeviceToken getByDeviceId(String deviceId);

    DeviceToken getByToken(String token);
}

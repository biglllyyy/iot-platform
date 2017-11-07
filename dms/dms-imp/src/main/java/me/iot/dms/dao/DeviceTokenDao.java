package me.iot.dms.dao;

import me.iot.dms.entity.DeviceToken;
import me.iot.util.jpa.BaseRepository;

/**
 * Created by sylar on 16/5/25.
 */
public interface DeviceTokenDao extends BaseRepository<DeviceToken, Long> {

    DeviceToken getByDeviceId(String deviceId);

    DeviceToken getByToken(String token);
}

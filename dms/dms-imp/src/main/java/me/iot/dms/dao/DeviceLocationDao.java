package me.iot.dms.dao;

import me.iot.dms.entity.DeviceLocation;
import me.iot.util.jpa.BaseRepository;

/**
 * Created by sylar on 16/5/25.
 */
public interface DeviceLocationDao extends BaseRepository<DeviceLocation, Long> {

    DeviceLocation getByDeviceId(String deviceId);
}

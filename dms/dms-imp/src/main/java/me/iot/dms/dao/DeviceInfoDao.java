package me.iot.dms.dao;

import me.iot.dms.entity.DeviceInfo;
import me.iot.util.jpa.BaseRepository;

/**
 * Created by sylar on 16/5/25.
 */
public interface DeviceInfoDao extends BaseRepository<DeviceInfo, Long> {

    DeviceInfo getByDeviceId(String deviceId);

    DeviceInfo getByMac(String mac);

    DeviceInfo getByBid(String bid);
}

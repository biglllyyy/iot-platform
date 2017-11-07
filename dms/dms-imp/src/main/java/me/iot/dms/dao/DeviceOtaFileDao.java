package me.iot.dms.dao;

import me.iot.dms.entity.DeviceOtaFile;
import me.iot.util.jpa.BaseRepository;

/**
 * Created by sylar on 16/5/25.
 */
public interface DeviceOtaFileDao extends BaseRepository<DeviceOtaFile, Long> {
    DeviceOtaFile getByDeviceTypeAndVersionCode(String deviceType, int versionCode);
}

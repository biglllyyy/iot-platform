package me.iot.dms.dao;

import me.iot.dms.entity.DeviceOtaFile;
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
public interface DeviceOtaFileDao extends BaseRepository<DeviceOtaFile, Long> {
    DeviceOtaFile getByDeviceTypeAndVersionCode(String deviceType, int versionCode);
}

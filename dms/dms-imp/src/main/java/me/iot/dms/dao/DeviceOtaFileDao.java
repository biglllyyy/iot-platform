package me.iot.dms.dao;

import me.iot.dms.entity.DeviceOtaFile;
import me.iot.util.jpa.BaseRepository;

/**
 * @author :  sylar
 * @FileName :  DeviceOtaFileDao
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
public interface DeviceOtaFileDao extends BaseRepository<DeviceOtaFile, Long> {
    /**
     * 根据设备类型和版本号获取设备在线升级文件
     *
     * @param deviceType
     * @param versionCode
     * @return
     */
    DeviceOtaFile getByDeviceTypeAndVersionCode(String deviceType, int versionCode);
}

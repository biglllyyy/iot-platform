package iot.dmp.dms.dao;

import iot.dmp.dms.po.DeviceOtaFile;
import iot.util.data.jpa.BaseJpaRepository;

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
public interface DeviceOtaFileDao extends BaseJpaRepository<DeviceOtaFile, Long> {
    /**
     * 根据设备类型和版本号获取设备在线升级文件
     *
     * @param deviceType
     * @param versionCode
     * @return
     */
    DeviceOtaFile getByDeviceTypeAndVersionCode(String deviceType, int versionCode);
}

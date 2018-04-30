package iot.dmp.dms.service;

import iot.dmp.dms.dao.DeviceOwnerDao;
import iot.dmp.dms.IDeviceOwnerService;
import iot.dmp.dms.po.DeviceOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author :  luhao
 * @FileName :  DeviceOwnerServiceImpl
 * @CreateDate :  2016/6/28
 * @Description :
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @CopyRight : COPYRIGHT(c) www.XXXXX.com   All Rights Reserved
 * *******************************************************************************************
 */
@Service
public class DeviceOwnerServiceImpl implements IDeviceOwnerService {

    private static Logger logger = LoggerFactory.getLogger(DeviceOwnerServiceImpl.class);

    @Autowired
    private DeviceOwnerDao deviceOwnerDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void bindDevice(String ownerId, String deviceId) {
        //查询device是否已经绑定
        DeviceOwner persistDeviceOwner = deviceOwnerDao.getDeviceOwnerByDeviceId(deviceId);
        if (persistDeviceOwner != null) {
            logger.warn("设备id为:[" + deviceId + "]已经被" +
                    " ownerId:[" + persistDeviceOwner.getOwnerId() + "]绑定，请检查。");

            return;
        }

        DeviceOwner deviceOwner = new DeviceOwner();
        deviceOwner.setOwnerId(ownerId);
        deviceOwner.setDeviceId(deviceId);
        deviceOwner.setBindDatetime(System.currentTimeMillis());
        deviceOwner.setBound(true);

        deviceOwnerDao.save(deviceOwner);
    }

    @Override
    public void bindDevice(String ownerId, List<String> deviceList) {
        if (deviceList == null || deviceList.isEmpty()) {
            logger.warn("设备列表为空。");
            return;
        }

        for (String deviceId : deviceList) {
            bindDevice(ownerId, deviceId);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void unBindDevice(String ownerId) {
        deviceOwnerDao.unBindDeviceOwner(ownerId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void unBindDevice(String ownerId, List<String> deviceList) {
        deviceOwnerDao.unBindDeviceOwner(ownerId, deviceList);
    }

    @Override
    public String getOwnerIdByDeviceId(String deviceId) {
        DeviceOwner persistDeviceOwner = deviceOwnerDao.getDeviceOwnerByDeviceId(deviceId);
        if (persistDeviceOwner != null) {
            return persistDeviceOwner.getOwnerId();
        }
        return null;
    }

    /**
     * 设备批量升级
     *
     * @param list 升级设备列表
     * @return 升级是否成功
     */
    @Override
    public String deviceUpdate(List list) {
        return null;
    }

}

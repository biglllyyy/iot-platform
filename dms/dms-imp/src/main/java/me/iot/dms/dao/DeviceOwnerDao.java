package me.iot.dms.dao;

import me.iot.dms.entity.DeviceOwner;
import me.iot.util.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @FileName             :  DeviceOwnerDao
 * @Author                :  luhao
 * @CreateDate           :  2016/6/28
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @CopyRight             : COPYRIGHT(c) www.XXXXX.com   All Rights Reserved
 * *******************************************************************************************
 */
public interface DeviceOwnerDao extends BaseRepository<DeviceOwner, Long> {

    /**
     * 根据deviceid获取deviceOwner
     *
     * @param deviceId
     * @return
     */
    @Query(" from DeviceOwner where deviceId=:deviceId and isBound=true ")
    DeviceOwner getDeviceOwnerByDeviceId(@Param("deviceId") String deviceId);


    /**
     * 解除绑定拥有者的所有设备
     *
     * @param ownerId
     */
    @Modifying
    @Query(" update DeviceOwner set isBound = false, unBindDatetime=UNIX_TIMESTAMP() where ownerId=:ownerId and isBound=true ")
    void unBindDeviceOwner(@Param("ownerId") String ownerId);


    /**
     * 解除绑定指定的设备
     *
     * @param ownerId
     * @param deviceList
     */
    @Modifying
    @Query(" update DeviceOwner set isBound = false, unBindDatetime=UNIX_TIMESTAMP() where ownerId=:ownerId and deviceId in( :deviceList ) and isBound=true ")
    void unBindDeviceOwner(@Param("ownerId") String ownerId, @Param("deviceList") List<String> deviceList);
}

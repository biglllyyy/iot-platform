package me.cloud.iot.store.dustbin.data.dao;

import me.cloud.iot.store.dustbin.data.entity.DustbinParam;
import me.iot.util.jpa.BaseRepository;

/**
 * @FileName             :  IDustbinParamDao
 * @Author                :  sylar
 * @CreateDate           :  2016/9/22
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
public interface IDustbinParamDao extends BaseRepository<DustbinParam, Long> {

    /**
     * get the record by the deviceId of the dustbin
     * @param deviceId
     * @return
     */
    DustbinParam getByDeviceId(String deviceId);
}

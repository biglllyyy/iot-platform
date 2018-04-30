package iot.quickstart.dustbin.data.dao;

import iot.quickstart.dustbin.data.entity.DustbinParam;
import iot.util.data.jpa.BaseJpaRepository;

/**
 * @author :  sylar
 * @FileName :  IDustbinParamDao
 * @CreateDate :  2016/9/22
 * @Description :
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @CopyRight : COPYRIGHT(c) me.iot.com   All Rights Reserved
 * *******************************************************************************************
 */
public interface IDustbinParamDao extends BaseJpaRepository<DustbinParam, Long> {

    /**
     * get the record by the deviceId of the dustbin
     *
     * @param deviceId
     * @return
     */
    DustbinParam getByDeviceId(String deviceId);
}

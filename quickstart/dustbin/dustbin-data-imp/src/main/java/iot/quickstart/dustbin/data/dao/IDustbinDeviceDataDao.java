package iot.quickstart.dustbin.data.dao;

import iot.quickstart.dustbin.data.entity.DustbinDeviceData;
import iot.util.data.jpa.BaseJpaRepository;

/**
 * @author :  luhao
 * @FileName :  IDustbinDeviceDataDao
 * @CreateDate :  2017/11/17
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
public interface IDustbinDeviceDataDao extends BaseJpaRepository<DustbinDeviceData, Long> {
}

package iot.dmp.dms;

import iot.dmp.dms.dto.DcsStatusDto;

/**
 * @author :  sylar
 * @FileName :
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
public interface IDcsStatusService {
    /**
     * get 设备接入服务的状态
     *
     * @param nodeId
     * @return
     */
    DcsStatusDto getDasStatus(String nodeId);
}

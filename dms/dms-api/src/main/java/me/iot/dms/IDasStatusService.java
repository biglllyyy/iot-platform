package me.iot.dms;

import me.iot.dms.entity.DasStatus;

/**
 * @author :  sylar
 * @FileName :  MqttConst
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
public interface IDasStatusService {
    /**
     * @param nodeId
     * @return
     */
    DasStatus getDasStatus(String nodeId);
}

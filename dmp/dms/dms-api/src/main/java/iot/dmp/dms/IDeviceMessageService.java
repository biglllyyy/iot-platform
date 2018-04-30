package iot.dmp.dms;

import iot.common.msg.IMsg;

/**
 * @author :  sylar
 * @FileName :  IDeviceMessageService
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
public interface IDeviceMessageService {

    /**
     * dms发送消息到das
     *
     * @param msg
     */
    void sendMsg(IMsg msg) throws Exception;

}

package iot.dcp.common.core;

import iot.common.msg.IMsg;

/**
 * @author :  sylar
 * @FileName :  IResponder
 * @CreateDate :  2017/11/08
 * @Description : 消息应答处理器（须开发人员自行实现）
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @CopyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public interface IResponder {

    /**
     * 应答处理
     *
     * @param msg 上行消息
     * @return 处理完后, 是否需要向上层传递(进入消息队列)
     */
    boolean onRespond(IMsg msg);
}

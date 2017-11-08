package me.iot.das.common.core;

import me.iot.common.msg.IMsg;

/**
 * @FileName             :  MqttConst
 * @Author                :  sylar
 * @CreateDate           :  2017/11/08
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @CopyRight             : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
/**
 * inbound消息处理器
 */
public interface InboundMsgProcessor {

    /**
     * 处理消息
     *
     * @param msg 收到的消息
     * @return 处理完后, 是否需要向上层传递(进入消息队列)
     */
    boolean processInboundMsg(IMsg msg);
}

package me.iot.das.mqtt.core;

import me.iot.common.msg.IMsg;
import me.iot.das.mqtt.pojo.MqttPacketWrap;

import java.util.List;

/**
 * @author :  sylar
 * @FileName :  IMqttMsgResolver
 * @CreateDate :  2017/11/08
 * @Description :
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @@CopyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public interface IMqttMsgResolver {

    /**
     * 将ByteBuffer转换成IMsg
     *
     * @param wrap ByteBuffer数据
     * @return IMsg列表
     */
    List<IMsg> wrapToMsg(MqttPacketWrap wrap);

    /**
     * 将IMsg转换成 ByteBuffer
     *
     * @param msg IMsg信息
     * @return ByteBuffer封装信息
     */
    MqttPacketWrap msgToWrap(IMsg msg);
}

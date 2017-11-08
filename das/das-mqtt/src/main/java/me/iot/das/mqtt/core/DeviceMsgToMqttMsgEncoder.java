package me.iot.das.mqtt.core;

import me.iot.das.mqtt.pojo.MqttPacketWrap;
import me.iot.das.mqtt.protocol.message.PublishMessage;
import me.iot.das.mqtt.util.MqttUtil;
import me.iot.common.msg.IMsg;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @FileName             :  DeviceMsgToMqttMsgEncoder
 * @@Author               :  sylar
 * @CreateDate           :  2017/11/08
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @@CopyRight            : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
@Component
@ChannelHandler.Sharable
public class DeviceMsgToMqttMsgEncoder extends MessageToMessageEncoder<IMsg> {
    private static final Logger LOG = LoggerFactory.getLogger(DeviceMsgToMqttMsgEncoder.class);

    @Autowired
    IMqttMsgResolver msgResolver;

    @Override
    protected void encode(ChannelHandlerContext ctx, IMsg msg, List<Object> out) throws Exception {
        try {
            MqttPacketWrap wrap = msgResolver.msgToWrap(msg);
            if (wrap != null) {
                PublishMessage pubMsg = MqttUtil.createPublishMessage(wrap);
                if (pubMsg != null) {
                    out.add(pubMsg);
                }
            }
        } catch (Exception e) {
            LOG.error("DeviceMsgToMqttMsgEncoder errr: {}", e.getMessage());
            e.printStackTrace();
        }
    }
}

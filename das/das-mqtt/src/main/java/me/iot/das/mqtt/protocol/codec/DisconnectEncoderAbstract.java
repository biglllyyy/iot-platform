package me.iot.das.mqtt.protocol.codec;

import me.iot.das.mqtt.protocol.message.AbstractMessage;
import me.iot.das.mqtt.protocol.message.DisconnectMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

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
public class DisconnectEncoderAbstract extends AbstractDemuxEncoder<DisconnectMessage> {

    @Override
    protected void encode(ChannelHandlerContext chc, DisconnectMessage msg, ByteBuf out) {
        out.writeByte(AbstractMessage.DISCONNECT << 4).writeByte(0);
    }

}

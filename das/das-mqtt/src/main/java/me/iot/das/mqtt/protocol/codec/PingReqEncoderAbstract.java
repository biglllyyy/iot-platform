package me.iot.das.mqtt.protocol.codec;

import me.iot.das.mqtt.protocol.message.AbstractMessage;
import me.iot.das.mqtt.protocol.message.PingReqMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

class PingReqEncoderAbstract extends AbstractDemuxEncoder<PingReqMessage> {

    @Override
    protected void encode(ChannelHandlerContext chc, PingReqMessage msg, ByteBuf out) {
        out.writeByte(AbstractMessage.PINGREQ << 4).writeByte(0);
    }
}

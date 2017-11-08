package me.iot.das.mqtt.protocol.codec;

import me.iot.das.mqtt.protocol.message.AbstractMessage;
import me.iot.das.mqtt.protocol.message.PingRespMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

class PingRespEncoderAbstract extends AbstractDemuxEncoder<PingRespMessage> {

    @Override
    protected void encode(ChannelHandlerContext chc, PingRespMessage msg, ByteBuf out) {
        out.writeByte(AbstractMessage.PINGRESP << 4).writeByte(0);
    }
}

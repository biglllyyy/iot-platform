package me.iot.das.mqtt.protocol.codec;

import me.iot.das.mqtt.protocol.message.AbstractMessage;
import me.iot.das.mqtt.protocol.message.ConnAckMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

class ConnAckEncoder extends DemuxEncoder<ConnAckMessage> {

    @Override
    protected void encode(ChannelHandlerContext chc, ConnAckMessage message, ByteBuf out) {
        out.writeByte(AbstractMessage.CONNACK << 4);
        out.writeBytes(CodecUtil.encodeRemainingLength(2));
        out.writeByte(message.isSessionPresent() ? 0x01 : 0x00);
        out.writeByte(message.getReturnCode());
    }

}

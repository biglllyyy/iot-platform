package me.iot.das.mqtt.protocol.codec;

import me.iot.das.mqtt.protocol.message.AbstractMessage;
import me.iot.das.mqtt.protocol.message.UnsubAckMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

class UnsubAckEncoder extends DemuxEncoder<UnsubAckMessage> {

    @Override
    protected void encode(ChannelHandlerContext chc, UnsubAckMessage msg, ByteBuf out) {
        out.writeByte(AbstractMessage.UNSUBACK << 4).
                writeBytes(CodecUtil.encodeRemainingLength(2)).
                writeShort(msg.getMessageID());
    }
}
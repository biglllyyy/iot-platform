package me.iot.das.mqtt.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import me.iot.das.mqtt.protocol.message.AbstractMessage;
import me.iot.das.mqtt.protocol.message.UnsubAckMessage;

class UnsubAckEncoderAbstract extends AbstractDemuxEncoder<UnsubAckMessage> {

    @Override
    protected void encode(ChannelHandlerContext chc, UnsubAckMessage msg, ByteBuf out) {
        out.writeByte(AbstractMessage.UNSUBACK << 4).
                writeBytes(CodecUtil.encodeRemainingLength(2)).
                writeShort(msg.getMessageID());
    }
}
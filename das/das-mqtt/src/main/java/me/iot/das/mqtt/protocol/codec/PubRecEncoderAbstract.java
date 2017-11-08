package me.iot.das.mqtt.protocol.codec;

import me.iot.das.mqtt.protocol.message.AbstractMessage;
import me.iot.das.mqtt.protocol.message.PubRecMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

class PubRecEncoderAbstract extends AbstractDemuxEncoder<PubRecMessage> {

    @Override
    protected void encode(ChannelHandlerContext chc, PubRecMessage msg, ByteBuf out) {
        out.writeByte(AbstractMessage.PUBREC << 4);
        out.writeBytes(CodecUtil.encodeRemainingLength(2));
        out.writeShort(msg.getMessageID());
    }
}
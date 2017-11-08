package me.iot.das.mqtt.protocol.codec;

import me.iot.das.mqtt.protocol.message.AbstractMessage;
import me.iot.das.mqtt.protocol.message.PubRelMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

class PubRelEncoderAbstract extends AbstractDemuxEncoder<PubRelMessage> {

    @Override
    protected void encode(ChannelHandlerContext chc, PubRelMessage msg, ByteBuf out) {
        out.writeByte(AbstractMessage.PUBREL << 4);
        out.writeBytes(CodecUtil.encodeRemainingLength(2));
        out.writeShort(msg.getMessageID());
    }
}
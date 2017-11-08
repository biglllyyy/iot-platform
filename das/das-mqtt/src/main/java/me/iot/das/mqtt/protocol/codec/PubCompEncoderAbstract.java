package me.iot.das.mqtt.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import me.iot.das.mqtt.protocol.message.AbstractMessage;
import me.iot.das.mqtt.protocol.message.PubCompMessage;

class PubCompEncoderAbstract extends AbstractDemuxEncoder<PubCompMessage> {

    @Override
    protected void encode(ChannelHandlerContext chc, PubCompMessage msg, ByteBuf out) {
        out.writeByte(AbstractMessage.PUBCOMP << 4);
        out.writeBytes(CodecUtil.encodeRemainingLength(2));
        out.writeShort(msg.getMessageID());
    }
}

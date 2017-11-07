package me.iot.das.mqtt.protocol.codec;

import me.iot.das.mqtt.protocol.message.AbstractMessage;
import me.iot.das.mqtt.protocol.message.PubCompMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

class PubCompEncoder extends DemuxEncoder<PubCompMessage> {

    @Override
    protected void encode(ChannelHandlerContext chc, PubCompMessage msg, ByteBuf out) {
        out.writeByte(AbstractMessage.PUBCOMP << 4);
        out.writeBytes(CodecUtil.encodeRemainingLength(2));
        out.writeShort(msg.getMessageID());
    }
}

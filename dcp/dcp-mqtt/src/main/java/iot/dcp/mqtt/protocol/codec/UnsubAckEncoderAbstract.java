package iot.dcp.mqtt.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import iot.dcp.mqtt.protocol.message.AbstractMessage;
import iot.dcp.mqtt.protocol.message.UnsubAckMessage;

class UnsubAckEncoderAbstract extends AbstractDemuxEncoder<UnsubAckMessage> {

    @Override
    protected void encode(ChannelHandlerContext chc, UnsubAckMessage msg, ByteBuf out) {
        out.writeByte(AbstractMessage.UNSUBACK << 4).
                writeBytes(CodecUtil.encodeRemainingLength(2)).
                writeShort(msg.getMessageID());
    }
}
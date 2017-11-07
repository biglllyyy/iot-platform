package me.iot.das.mqtt.protocol.codec;

import me.iot.das.mqtt.protocol.message.AbstractMessage;
import me.iot.das.mqtt.protocol.message.UnsubscribeMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;


class UnsubscribeEncoder extends DemuxEncoder<UnsubscribeMessage> {

    @Override
    protected void encode(ChannelHandlerContext chc, UnsubscribeMessage message, ByteBuf out) {
        if (message.topicFilters().isEmpty()) {
            throw new IllegalArgumentException("Found an unsubscribe message with empty topics");
        }

        if (message.getQos() != AbstractMessage.QOSType.LEAST_ONE) {
            throw new IllegalArgumentException("Expected a message with QOS 1, found " + message.getQos());
        }

        ByteBuf variableHeaderBuff = chc.alloc().buffer(4);
        ByteBuf buff = null;
        try {
            variableHeaderBuff.writeShort(message.getMessageID());
            for (String topic : message.topicFilters()) {
                variableHeaderBuff.writeBytes(CodecUtil.encodeString(topic));
            }

            int variableHeaderSize = variableHeaderBuff.readableBytes();
            byte flags = CodecUtil.encodeFlags(message);
            buff = chc.alloc().buffer(2 + variableHeaderSize);

            buff.writeByte(AbstractMessage.UNSUBSCRIBE << 4 | flags);
            buff.writeBytes(CodecUtil.encodeRemainingLength(variableHeaderSize));
            buff.writeBytes(variableHeaderBuff);

            out.writeBytes(buff);
        } finally {
            variableHeaderBuff.release();
            if (buff != null) {
                buff.release();
            }
        }
    }

}

package me.iot.das.mqtt.protocol.codec;

import me.iot.das.mqtt.protocol.message.AbstractMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

abstract class AbstractDemuxEncoder<T extends AbstractMessage> {
    abstract protected void encode(ChannelHandlerContext chc, T msg, ByteBuf bb);
}

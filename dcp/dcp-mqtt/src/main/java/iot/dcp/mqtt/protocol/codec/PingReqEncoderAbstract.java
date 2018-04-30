package iot.dcp.mqtt.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import iot.dcp.mqtt.protocol.message.AbstractMessage;
import iot.dcp.mqtt.protocol.message.PingReqMessage;

class PingReqEncoderAbstract extends AbstractDemuxEncoder<PingReqMessage> {

    @Override
    protected void encode(ChannelHandlerContext chc, PingReqMessage msg, ByteBuf out) {
        out.writeByte(AbstractMessage.PINGREQ << 4).writeByte(0);
    }
}

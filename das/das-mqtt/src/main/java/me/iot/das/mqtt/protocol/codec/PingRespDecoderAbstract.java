package me.iot.das.mqtt.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.util.AttributeMap;
import me.iot.das.mqtt.protocol.message.PingRespMessage;

import java.util.List;

class PingRespDecoderAbstract extends AbstractDemuxDecoder {

    @Override
    void decode(AttributeMap ctx, ByteBuf in, List<Object> out) throws Exception {
        //Common decoding part
        in.resetReaderIndex();
        PingRespMessage message = new PingRespMessage();
        if (!decodeCommonHeader(message, 0x00, in)) {
            in.resetReaderIndex();
            return;
        }
        out.add(message);
    }
}

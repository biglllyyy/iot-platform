package me.iot.das.mqtt.protocol.codec;

import me.iot.das.mqtt.protocol.message.PingReqMessage;
import io.netty.buffer.ByteBuf;
import io.netty.util.AttributeMap;

import java.util.List;

class PingReqDecoder extends DemuxDecoder {

    @Override
    void decode(AttributeMap ctx, ByteBuf in, List<Object> out) throws Exception {
        //Common decoding part
        in.resetReaderIndex();
        PingReqMessage message = new PingReqMessage();
        if (!decodeCommonHeader(message, 0x00, in)) {
            in.resetReaderIndex();
            return;
        }
        out.add(message);
    }
}

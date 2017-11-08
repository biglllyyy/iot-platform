package me.iot.das.mqtt.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.util.AttributeMap;
import me.iot.das.mqtt.protocol.message.DisconnectMessage;

import java.util.List;

class DisconnectDecoderAbstract extends AbstractDemuxDecoder {

    @Override
    void decode(AttributeMap ctx, ByteBuf in, List<Object> out) throws Exception {
        //Common decoding part
        in.resetReaderIndex();
        DisconnectMessage message = new DisconnectMessage();
        if (!decodeCommonHeader(message, 0x00, in)) {
            in.resetReaderIndex();
            return;
        }
        out.add(message);
    }

}

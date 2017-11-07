package me.iot.das.mqtt.protocol.codec;

import me.iot.das.mqtt.protocol.message.MessageIDMessage;
import io.netty.buffer.ByteBuf;
import io.netty.util.AttributeMap;

import java.util.List;

abstract class MessageIDDecoder extends DemuxDecoder {

    protected abstract MessageIDMessage createMessage();

    @Override
    void decode(AttributeMap ctx, ByteBuf in, List<Object> out) throws Exception {
        in.resetReaderIndex();
        //Common decoding part
        MessageIDMessage message = createMessage();
        if (!decodeCommonHeader(message, 0x00, in)) {
            in.resetReaderIndex();
            return;
        }

        //read  messageIDs
        message.setMessageID(in.readUnsignedShort());
        out.add(message);
    }

}

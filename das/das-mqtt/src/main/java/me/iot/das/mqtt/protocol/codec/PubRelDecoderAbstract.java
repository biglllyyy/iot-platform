package me.iot.das.mqtt.protocol.codec;

import me.iot.das.mqtt.protocol.message.MessageIDMessage;
import me.iot.das.mqtt.protocol.message.PubRelMessage;
import io.netty.buffer.ByteBuf;
import io.netty.util.AttributeMap;

import java.io.UnsupportedEncodingException;
import java.util.List;

class PubRelDecoderAbstract extends AbstractDemuxDecoder {

    @Override
    void decode(AttributeMap ctx, ByteBuf in, List<Object> out) throws UnsupportedEncodingException {
        in.resetReaderIndex();
        //Common decoding part
        int expectedFlags = 0x02;
        MessageIDMessage message = new PubRelMessage();
        if (!decodeCommonHeader(message, expectedFlags, in)) {
            in.resetReaderIndex();
            return;
        }

        //read  messageIDs
        message.setMessageID(in.readUnsignedShort());
        out.add(message);
    }

}


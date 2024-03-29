package iot.dcp.mqtt.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.util.AttributeMap;
import iot.dcp.mqtt.protocol.message.MessageIDMessage;
import iot.dcp.mqtt.protocol.message.PubRelMessage;

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


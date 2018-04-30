package iot.dcp.mqtt.protocol.codec;

import iot.dcp.mqtt.protocol.message.MessageIDMessage;
import iot.dcp.mqtt.protocol.message.PubCompMessage;


class PubCompDecoderAbstractAbstractMessageIDDecoder extends AbstractMessageIDDecoder {

    @Override
    protected MessageIDMessage createMessage() {
        return new PubCompMessage();
    }
}

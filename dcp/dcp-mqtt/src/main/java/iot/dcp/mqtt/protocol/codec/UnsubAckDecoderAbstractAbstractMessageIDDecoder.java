package iot.dcp.mqtt.protocol.codec;

import iot.dcp.mqtt.protocol.message.MessageIDMessage;
import iot.dcp.mqtt.protocol.message.UnsubAckMessage;

class UnsubAckDecoderAbstractAbstractMessageIDDecoder extends AbstractMessageIDDecoder {

    @Override
    protected MessageIDMessage createMessage() {
        return new UnsubAckMessage();
    }
}


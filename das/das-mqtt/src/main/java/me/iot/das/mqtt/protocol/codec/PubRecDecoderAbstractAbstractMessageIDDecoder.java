package me.iot.das.mqtt.protocol.codec;

import me.iot.das.mqtt.protocol.message.MessageIDMessage;
import me.iot.das.mqtt.protocol.message.PubRecMessage;

class PubRecDecoderAbstractAbstractMessageIDDecoder extends AbstractMessageIDDecoder {

    @Override
    protected MessageIDMessage createMessage() {
        return new PubRecMessage();
    }
}

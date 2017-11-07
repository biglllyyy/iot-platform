package me.iot.das.mqtt.protocol.codec;

import me.iot.das.mqtt.protocol.message.MessageIDMessage;
import me.iot.das.mqtt.protocol.message.UnsubAckMessage;

class UnsubAckDecoder extends MessageIDDecoder {

    @Override
    protected MessageIDMessage createMessage() {
        return new UnsubAckMessage();
    }
}


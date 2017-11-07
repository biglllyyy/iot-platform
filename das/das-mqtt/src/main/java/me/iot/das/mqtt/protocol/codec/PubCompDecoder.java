package me.iot.das.mqtt.protocol.codec;

import me.iot.das.mqtt.protocol.message.MessageIDMessage;
import me.iot.das.mqtt.protocol.message.PubCompMessage;


class PubCompDecoder extends MessageIDDecoder {

    @Override
    protected MessageIDMessage createMessage() {
        return new PubCompMessage();
    }
}

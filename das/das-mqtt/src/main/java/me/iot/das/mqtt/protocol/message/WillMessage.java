package me.iot.das.mqtt.protocol.message;

import java.nio.ByteBuffer;

public class WillMessage extends PublishMessage {

    public WillMessage(String willTopic, ByteBuffer willMessage, boolean willRetain, QOSType willQos) {
        super();
        setTopicName(willTopic);
        setPayload(willMessage);
        setRetainFlag(willRetain);
        setQos(willQos);
    }

}

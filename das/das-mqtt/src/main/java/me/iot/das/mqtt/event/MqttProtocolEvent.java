package me.iot.das.mqtt.event;

import me.iot.das.mqtt.protocol.message.AbstractMessage;
import io.netty.channel.Channel;

public class MqttProtocolEvent extends MqttEvent {
    private AbstractMessage message;

    public MqttProtocolEvent(Object source, Channel channel, AbstractMessage message) {
        super(source, channel);
        this.message = message;
    }

    public AbstractMessage getMessage() {
        return message;
    }
}

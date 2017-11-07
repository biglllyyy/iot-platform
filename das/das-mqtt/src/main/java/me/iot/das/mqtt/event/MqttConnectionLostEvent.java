package me.iot.das.mqtt.event;

import io.netty.channel.Channel;

public class MqttConnectionLostEvent extends MqttEvent {
    private String clientId;

    public MqttConnectionLostEvent(Object source, Channel channel, String clientId) {
        super(source, channel);
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

}

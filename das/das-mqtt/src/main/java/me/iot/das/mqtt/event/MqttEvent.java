package me.iot.das.mqtt.event;

import io.netty.channel.Channel;
import org.springframework.context.ApplicationEvent;

/**
 * Created by sylar on 16/6/2.
 */
public class MqttEvent extends ApplicationEvent {
    private Channel channel;

    public MqttEvent(Object source, Channel channel) {
        super(source);
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

}

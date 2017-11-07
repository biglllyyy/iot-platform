package me.iot.das.common.event;

import me.iot.common.msg.IMsg;
import io.netty.channel.Channel;
import org.springframework.context.ApplicationEvent;

public class ChannelMsgEvent extends ApplicationEvent {
    private Channel channel;
    private IMsg msg;

    public ChannelMsgEvent(Object source, Channel channel, IMsg msg) {
        super(source);
        this.channel = channel;
        this.msg = msg;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public IMsg getMsg() {
        return msg;
    }

    public void setMsg(IMsg msg) {
        this.msg = msg;
    }
}

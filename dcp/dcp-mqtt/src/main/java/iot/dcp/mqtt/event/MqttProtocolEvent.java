package iot.dcp.mqtt.event;

import io.netty.channel.Channel;
import iot.dcp.mqtt.protocol.message.AbstractMessage;

/**
 * @author :  sylar
 * @FileName :
 * @CreateDate :  2017/11/08
 * @Description :
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @CopyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
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

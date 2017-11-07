package me.iot.util.mq;

import com.google.common.base.Preconditions;

import java.util.Properties;

public abstract class AbstractProcuder extends AbstractClient implements IProducer {

    @Override
    public void start() throws Exception {
        stop();
    }

    @Override
    public void send(Message message) throws Exception {
        Preconditions.checkNotNull(message, "message is null");
        Preconditions.checkNotNull(message.getTopic(), "message topic is null");
        Preconditions.checkNotNull(message.getContent(), "message content is null");
    }

    @Override
    public void stop() {

    }
}

package me.iot.util.mq;

import com.google.common.base.Preconditions;

import java.util.Properties;

public abstract class AbstractProcuder implements IProducer {
    protected String brokers;
    protected Properties properties = new Properties();

    public AbstractProcuder(String brokers) {
        this(brokers, null);
    }

    public AbstractProcuder(String brokers, Properties properties) {
        Preconditions.checkNotNull(brokers, "brokers is null");

        this.brokers = brokers;
        setProperties(properties);
    }

    @Override
    public void setProperties(Properties properties) {
        if (null != properties) {
            this.properties.putAll(properties);
        }
    }

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

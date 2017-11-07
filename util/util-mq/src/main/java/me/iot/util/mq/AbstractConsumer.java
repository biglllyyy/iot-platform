package me.iot.util.mq;

import com.google.common.base.Preconditions;

import java.util.List;
import java.util.Properties;

public abstract class AbstractConsumer implements IConsumer {

    protected String brokers;
    protected Properties properties = new Properties();

    public AbstractConsumer(String brokers) {
        this(brokers, null);
    }

    public AbstractConsumer(String brokers, Properties properties) {
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
    public void subscribe(List<String> topics, MessageListener messageListener) {
        Preconditions.checkNotNull(topics, "topics is null");
        Preconditions.checkNotNull(messageListener, "messageListener is null");

        Preconditions.checkState(topics.size() > 0, "topics is empty");

        unsubscribe();
    }
}

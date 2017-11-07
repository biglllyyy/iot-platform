package me.iot.util.mq.rocketmq;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.google.common.base.Charsets;
import me.iot.util.mq.AbstractProcuder;
import me.iot.util.mq.Message;

import java.util.Properties;


/**
 *
 */
public class RocketmqProcuder extends AbstractProcuder {

    protected DefaultMQProducer producer;

    public RocketmqProcuder(String brokers) {
        super(brokers);
    }

    public RocketmqProcuder(String brokers, Properties properties) {
        super(brokers, properties);
    }

    @Override
    public void start() throws Exception {
        super.start();

        producer = new DefaultMQProducer();
        producer.setNamesrvAddr(brokers);
        producer.setVipChannelEnabled(false);

        producer.start();
    }

    @Override
    public void send(Message message) throws Exception {
        super.send(message);
        producer.send(new com.alibaba.rocketmq.common.message.Message(message.getTopic(), message.getContent().getBytes(Charsets.UTF_8)));
    }

    @Override
    public void stop() {

        super.stop();

        if (producer != null) {
            producer.shutdown();
        }
    }

}

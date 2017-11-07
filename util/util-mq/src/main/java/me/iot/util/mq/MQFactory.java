package me.iot.util.mq;

import me.iot.util.mq.kafka.KafkaConsumer;
import me.iot.util.mq.kafka.KafkaProcuder;
import me.iot.util.mq.rocketmq.RocketmqConsumer;
import me.iot.util.mq.rocketmq.RocketmqProcuder;

public class MQFactory implements IFactory {

    private static MQFactory factory = new MQFactory();

    synchronized public static MQFactory getInstance() {
        return factory;
    }

    private MQFactory() {
    }

    @Override
    public IProducer createProducer(Provider provider, String brokers) {
        switch (provider) {
            case Kafka:
                return new KafkaProcuder(brokers);
            case Rocketmq:
                return new RocketmqProcuder(brokers);
            default:
                return null;
        }
    }

    @Override
    public IConsumer createConsumer(Provider provider, String brokers) {
        switch (provider) {
            case Kafka:
                return new KafkaConsumer(brokers);
            case Rocketmq:
                return new RocketmqConsumer(brokers);
            default:
                return null;
        }
    }
}

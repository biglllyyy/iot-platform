package me.iot.util.kafka;

import me.iot.util.kafka.consumer.IConsumerConfig;
import me.iot.util.kafka.consumer.SimpleConsumer;
import me.iot.util.kafka.producer.IProducerConfig;
import me.iot.util.kafka.producer.SimpleProcuder;

/**
 * Created by sylar on 2017/1/6.
 */
public class KafkaFactory implements IFactory {

    @Override
    public IProducer createProducer(IProducerConfig config) {
        return new SimpleProcuder(config);
    }

    @Override
    public IConsumer createConsumer(IConsumerConfig config) {
        return new SimpleConsumer(config);
    }
}

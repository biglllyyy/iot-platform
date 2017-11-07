package me.iot.util.kafka;

import me.iot.util.kafka.consumer.IConsumerConfig;
import me.iot.util.kafka.producer.IProducerConfig;

/**
 * Created by sylar on 2017/1/6.
 */
public interface IFactory {
    IProducer createProducer(IProducerConfig config);

    IConsumer createConsumer(IConsumerConfig config);
}

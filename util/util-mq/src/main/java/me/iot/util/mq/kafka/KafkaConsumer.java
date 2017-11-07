package me.iot.util.mq.kafka;

import me.iot.util.mq.AbstractConsumer;
import me.iot.util.mq.MessageListener;
import me.iot.util.mq.kafka.loop.PullLoop;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.List;
import java.util.Properties;

public class KafkaConsumer extends AbstractConsumer {

    protected org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer;
    protected PullLoop loop;

    public KafkaConsumer(String brokers) {
        super(brokers);
    }

    public KafkaConsumer(String brokers, Properties properties) {
        super(brokers, properties);
    }

    @Override
    public void subscribe(List<String> topics, MessageListener messageListener) {
        super.subscribe(topics, messageListener);

        try {
            initConsumer();
            consumer.subscribe(topics);

            loop = new PullLoop(consumer, messageListener);
            loop.start();
        } catch (Exception e) {
            messageListener.onFailure(e);
        }

    }

    @Override
    public void unsubscribe() {

        if (consumer != null) {
            consumer.unsubscribe();
            consumer.close();
            consumer = null;
        }

        if (loop != null) {
            loop.stop();
            loop = null;
        }
    }

    private void initConsumer() {
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        properties.putIfAbsent(ConsumerConfig.GROUP_ID_CONFIG, "DEFAULT");

        /* 是否自动确认offset, 强制由业务层来确认 */
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(properties);
    }
}

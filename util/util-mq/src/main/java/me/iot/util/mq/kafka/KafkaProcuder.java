package me.iot.util.mq.kafka;

import me.iot.util.mq.AbstractProcuder;
import me.iot.util.mq.Message;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 *
 */
public class KafkaProcuder extends AbstractProcuder {

    protected KafkaProducer<String, String> producer;

    @Override
    public void start() throws Exception {
        super.start();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        //kafka producer 没有group概念，可省略
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        producer = new KafkaProducer<>(properties);
    }

    @Override
    public void stop() {
        super.stop();
        if (producer != null) {
            producer.close();
        }
    }

    @Override
    public void send(Message message) throws Exception {
        super.send(message);

        producer.send(new ProducerRecord<>(message.getTopic(), message.getContent()));
    }

}

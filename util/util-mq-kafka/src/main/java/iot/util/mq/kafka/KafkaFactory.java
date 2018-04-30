package iot.util.mq.kafka;

import iot.util.mq.AbstractFactory;
import iot.util.mq.IConsumer;
import iot.util.mq.IProducer;

/**
 * @author :  sylar
 * @FileName :  KafkaFactory
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
public class KafkaFactory extends AbstractFactory {

    @Override
    public IProducer createProducer(String brokers, String groupId, String clientId) {
        KafkaProducer producer = new KafkaProducer();
        setClient(producer, brokers, groupId, clientId);
        return producer;
    }

    @Override
    public IConsumer createConsumer(String brokers, String groupId, String clientId) {
        KafkaConsumer consumer = new KafkaConsumer();
        setClient(consumer, brokers, groupId, clientId);
        return consumer;
    }
}

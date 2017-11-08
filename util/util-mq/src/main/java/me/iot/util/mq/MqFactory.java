package me.iot.util.mq;

import me.iot.util.mq.kafka.KafkaConsumer;
import me.iot.util.mq.kafka.KafkaProcuder;
import me.iot.util.mq.rocketmq.RocketmqConsumer;
import me.iot.util.mq.rocketmq.RocketmqProcuder;

/**
 * @author :  sylar
 * @FileName :  MqttConst
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
public class MqFactory implements IFactory {

    private static MqFactory factory = new MqFactory();

    synchronized public static MqFactory getInstance() {
        return factory;
    }

    private MqFactory() {
    }

    @Override
    public IProducer createProducer(Provider provider) {
        switch (provider) {
            case Kafka:
                return new KafkaProcuder();
            case Rocketmq:
                return new RocketmqProcuder();
            default:
                return null;
        }
    }

    @Override
    public IConsumer createConsumer(Provider provider) {
        switch (provider) {
            case Kafka:
                return new KafkaConsumer();
            case Rocketmq:
                return new RocketmqConsumer();
            default:
                return null;
        }
    }
}

package iot.util.mq.ons.tcp;

import iot.util.mq.IConsumer;
import iot.util.mq.IProducer;
import iot.util.mq.ons.AbstractOnsFactory;

/**
 * @author :  sylar
 * @FileName :  OnsTcpFactory
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
public class OnsTcpFactory extends AbstractOnsFactory {

    public OnsTcpFactory(String accessKey, String secretKey) {
        super(accessKey, secretKey);
    }

    @Override
    public IProducer createProducer(String brokers, String groupId, String clientId) {
        OnsTcpProducer producer = new OnsTcpProducer();
        setClient(producer, brokers, groupId, clientId);
        setProducerKey(producer, accessKey, secretKey);
        return producer;
    }

    @Override
    public IConsumer createConsumer(String brokers, String groupId, String clientId) {
        OnsTcpConsumer consumer = new OnsTcpConsumer();
        setClient(consumer, brokers, groupId, clientId);
        setConsumerKey(consumer, accessKey, secretKey);
        return consumer;
    }
}

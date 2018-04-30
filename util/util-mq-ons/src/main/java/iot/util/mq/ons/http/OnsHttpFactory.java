package iot.util.mq.ons.http;

import iot.util.mq.IConsumer;
import iot.util.mq.IProducer;
import iot.util.mq.ons.AbstractOnsFactory;

/**
 * @author :  sylar
 * @FileName :  OnsHttpFactory
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
public class OnsHttpFactory extends AbstractOnsFactory {

    public OnsHttpFactory(String accessKey, String secretKey) {
        super(accessKey, secretKey);
    }

    @Override
    public IProducer createProducer(String brokers, String groupId, String clientId) {
        OnsHttpProducer producer = new OnsHttpProducer();
        setClient(producer, brokers, groupId, clientId);
        setProducerKey(producer, accessKey, secretKey);
        return producer;
    }

    @Override
    public IConsumer createConsumer(String brokers, String groupId, String clientId) {
        OnsHttpConsumer consumer = new OnsHttpConsumer();
        setClient(consumer, brokers, groupId, clientId);
        setConsumerKey(consumer, accessKey, secretKey);
        return consumer;
    }
}

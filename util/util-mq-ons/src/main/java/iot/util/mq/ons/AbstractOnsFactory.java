package iot.util.mq.ons;

import iot.util.mq.AbstractFactory;

/**
 * @author :  sylar
 * @FileName :  AbstractOnsFactory
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
public abstract class AbstractOnsFactory extends AbstractFactory {

    protected String accessKey;
    protected String secretKey;

    public AbstractOnsFactory(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    protected void setProducerKey(AbstractOnsProducer producer, String accessKey, String secretKey) {
        producer.setAccessKey(accessKey);
        producer.setSecretKey(secretKey);
    }

    protected void setConsumerKey(AbstractOnsConsumer consumer, String accessKey, String secretKey) {
        consumer.setAccessKey(accessKey);
        consumer.setSecretKey(secretKey);
    }

}

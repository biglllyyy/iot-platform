package me.iot.das.common;

import com.google.common.base.Preconditions;
import me.iot.util.mq.IConsumer;
import me.iot.util.mq.IProducer;
import me.iot.util.mq.MqFactory;
import me.iot.util.mq.Provider;
import me.iot.util.redis.ICentralCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * @author :  sylar
 * @FileName :  DasConfig
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
@Configuration
@EnableConfigurationProperties(value = {DasProperties.class})
public class DasConfig {

    @Autowired
    DasProperties dasProperties;

    @Autowired
    ICentralCacheService ccs;

//    @Autowired
//    IMessageQueueService mqs;
//
//    @Autowired
//    ISubscribePublishService sps;


    Provider provider = Provider.Rocketmq;
    String brokers = null;
    String groupId = null;
    String clientId = null;
    IProducer producer;
    IConsumer consumer;

    @PostConstruct
    private void init() {
        Preconditions.checkNotNull(dasProperties, "dasProperties can not be null");

        producer = MqFactory.getInstance().createProducer(provider);
        producer.setBasicParameter(brokers, groupId, clientId);

        consumer = MqFactory.getInstance().createConsumer(provider);
        consumer.setBasicParameter(brokers, groupId, clientId);
        consumer.setBroadcasting(false);
    }

    @PreDestroy
    private void dispose() {
    }

    public String getDasNodeId() {
        return dasProperties.getNodeId();
    }

    public DasProperties getDasProperties() {
        return dasProperties;
    }

    public ICentralCacheService getCcs() {
        return ccs;
    }

//    public IMessageQueueService getMqs() {
//        return mqs;
//    }
//
//    public ISubscribePublishService getSps() {
//        return sps;
//    }

    public IProducer getProducer() {
        return producer;
    }

    public IConsumer getConsumer() {
        return consumer;
    }
}

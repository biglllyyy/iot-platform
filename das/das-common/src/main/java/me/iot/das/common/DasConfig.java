package me.iot.das.common;

import com.google.common.base.Preconditions;
import me.iot.util.mq.IConsumer;
import me.iot.util.mq.IProducer;
import me.iot.util.mq.MQFactory;
import me.iot.util.mq.Provider;
import me.iot.util.redis.ICentralCacheService;
import me.iot.util.redis.IMessageQueueService;
import me.iot.util.redis.ISubscribePublishService;
import oracle.jvm.hotspot.jfr.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by sylar on 16/5/17.
 */
@Configuration
@EnableConfigurationProperties(value = {DasProperties.class})
public class DasConfig {

    @Autowired
    DasProperties dasProperties;

    @Autowired
    ICentralCacheService ccs;

    @Autowired
    IMessageQueueService mqs;

    @Autowired
    ISubscribePublishService sps;


    IProducer producer;
    IConsumer consumer;

    String brokers = null;
    Provider provider = Provider.Rocketmq;


    @PostConstruct
    private void init() {
        Preconditions.checkNotNull(dasProperties, "dasProperties can not be null");
        Preconditions.checkNotNull(dasProperties.getNodeId(), "das.nodeId can not be null");

        producer = MQFactory.getInstance().createProducer(provider, brokers);
        consumer = MQFactory.getInstance().createConsumer(provider, brokers);
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

    public IMessageQueueService getMqs() {
        return mqs;
    }

    public ISubscribePublishService getSps() {
        return sps;
    }

    public IProducer getProducer() {
        return producer;
    }

    public IConsumer getConsumer() {
        return consumer;
    }
}

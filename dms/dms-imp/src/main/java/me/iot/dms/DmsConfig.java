package me.iot.dms;

import me.iot.util.mq.IConsumer;
import me.iot.util.mq.IProducer;
import me.iot.util.mq.Provider;
import me.iot.util.redis.ICentralCacheService;
import me.iot.util.redis.IMessageQueueService;
import me.iot.util.redis.ISubscribePublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @FileName             :  MqttConst
 * @Author                :  sylar
 * @CreateDate           :  2017/11/08
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @CopyRight             : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
@Configuration
public class DmsConfig {

    @Value("${zookeeper.connectString}")
    String zkConnectString;

    @Autowired
    ICentralCacheService ccs;

    @Autowired
    IMessageQueueService mqs;

    @Autowired
    ISubscribePublishService sps;

//    ISimpleDataService sds;
//    IFileStorageService fss;

    IProducer producer;
    IConsumer consumer;

    String brokers = null;
    Provider provider = Provider.Rocketmq;

    @PostConstruct
    public void init() {
    }

    public String getZkConnectString() {
        return zkConnectString;
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

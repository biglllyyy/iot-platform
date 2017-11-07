package me.iot.dms;

import me.iot.util.mq.IConsumer;
import me.iot.util.mq.IProducer;
import me.iot.util.mq.Provider;
import me.iot.util.redis.ICentralCacheService;
import me.iot.util.redis.IMessageQueueService;
import me.iot.util.redis.ISubscribePublishService;
import me.iot.fss.FSS;
import me.iot.fss.IFileStorageService;
import me.iot.sds.SDS;
import me.iot.sds.service.ISimpleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by sylar on 16/5/17.
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
//        sds = SDS.getService(zkConnectString);
//        fss = FSS.getService(zkConnectString);
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

//    public ISimpleDataService getSds() {
//        return sds;
//    }
//
//    public IFileStorageService getFss() {
//        return fss;
//    }

    public IProducer getProducer() {
        return producer;
    }

    public IConsumer getConsumer() {
        return consumer;
    }
}

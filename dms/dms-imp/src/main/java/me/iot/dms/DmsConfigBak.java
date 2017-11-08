//package me.iot.dms;
//
//import me.iot.util.mq.IConsumer;
//import me.iot.util.mq.IProducer;
//import me.iot.util.mq.MQFactory;
//import me.iot.util.mq.Provider;
//import me.iot.util.redis.ICentralCacheService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//
///**
// * Created by sylar on 16/5/17.
// */
//@Configuration
//public class DmsConfigBak {
//
//    @Value("${zookeeper.connectString}")
//    String zkConnectString;
//
//    @Autowired
//    ICentralCacheService ccs;
//
////    @Autowired
////    IMessageQueueService mqs;
////
////    @Autowired
////    ISubscribePublishService sps;
//
////    ISimpleDataService sds;
////    IFileStorageService fss;
//
//    Provider provider = Provider.Rocketmq;
//    String brokers = null;
//    String groupId = null;
//    String clientId = null;
//    IProducer producer;
//    IConsumer consumer;
//
//
//    @PostConstruct
//    public void init() {
////        sds = SDS.getService(zkConnectString);
////        fss = FSS.getService(zkConnectString);
//
//        producer = MQFactory.getInstance().createProducer(provider);
//        producer.setBasicParameter(brokers, groupId, clientId);
//
//        consumer = MQFactory.getInstance().createConsumer(provider);
//        consumer.setBasicParameter(brokers, groupId, clientId);
//        consumer.setBroadcasting(false);
//    }
//
//    public String getZkConnectString() {
//        return zkConnectString;
//    }
//
//    public ICentralCacheService getCcs() {
//        return ccs;
//    }
////
////    public IMessageQueueService getMqs() {
////        return mqs;
////    }
////
////    public ISubscribePublishService getSps() {
////        return sps;
////    }
//
////    public ISimpleDataService getSds() {
////        return sds;
////    }
////
////    public IFileStorageService getFss() {
////        return fss;
////    }
//
//    public IProducer getProducer() {
//        return producer;
//    }
//
//    public IConsumer getConsumer() {
//        return consumer;
//    }
//}

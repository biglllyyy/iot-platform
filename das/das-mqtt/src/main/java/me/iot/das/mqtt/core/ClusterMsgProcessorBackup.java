//package me.iot.das.mqtt.core;
//
//import com.alibaba.fastjson.JSON;
//import com.google.common.collect.Lists;
//import com.lmax.disruptor.EventHandler;
//import me.iot.das.common.DasConfig;
//import me.iot.das.mqtt.MqttCacheKeys;
//import me.iot.das.mqtt.bean.MqttMsgSender;
//import me.iot.das.mqtt.protocol.message.PublishMessage;
//import me.iot.util.disruptor.IMessaging;
//import me.iot.util.disruptor.LmaxDiscuptorMessaging;
//import me.iot.util.disruptor.ValueEvent;
//import me.iot.util.redis.AbstractMessageListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.Set;
//
///**
// * 1\通过SPS订阅集群中其它DAS实例发本的当前节点所关注的topic消息
// * 2\通过SPS发布集群中其它DAS实例所关注的topic消息
// * <p>
// * 问题:
// * 1\json 不能反序列化成具体类型的 DeviceMsg
// * 2\非mqtt协议(不支持topic),无法做消息路由
// */
//@Component
//public class ClusterMsgProcessorBackup extends AbstractMessageListener implements EventHandler<ValueEvent> {
//    @Autowired
//    MqttMsgSender mqttMsgSender;
//
//    @Autowired
//    private DasConfig dasConfig;
//
//    private IMessaging messaging;
//
//    @PostConstruct
//    private void init() {
//        messaging = new LmaxDiscuptorMessaging(this);
//
//        String topic = MqttCacheKeys.getCcsKeyForTopicByNode(dasConfig.getDasNodeId());
//        dasConfig.getSps().subscribeMessage(this, Lists.newArrayList(topic));
//    }
//
//    public void processMsg(PublishMessage message) {
//        messaging.publish(message);
//    }
//
//    @Override
//    public void handleMessage(String topic, String jsonMsg) {
//        PublishMessage publishMessage = JSON.parseObject(jsonMsg, PublishMessage.class);
//        mqttMsgSender.send(publishMessage);
//    }
//
//    @Override
//    public void onEvent(ValueEvent event, long sequence, boolean endOfBatch)
//            throws Exception {
//        Object object = event.getValue();
//        if (object instanceof PublishMessage) {
//            PublishMessage msg = (PublishMessage) object;
//
//            String topic = msg.getTopicName();
//
//            //从CCS中查找订阅了这个topic的所有 das nodeId
//            String topicKey = MqttCacheKeys.getCcsKeyForNodesByTopic(topic);
//            Set<String> nodeSet = dasConfig.getCcs().getObjectsFromSet(topicKey, String.class);
//            for (String nodeId : nodeSet) {
//                //通过sps将消息发给其它 das node
//                String nodeTopic = MqttCacheKeys.getCcsKeyForTopicByNode(nodeId);
//                dasConfig.getSps().publishMessage(nodeTopic, msg);
//            }
//        }
//    }
//}

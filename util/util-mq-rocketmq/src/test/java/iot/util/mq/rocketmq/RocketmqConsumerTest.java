package iot.util.mq.rocketmq;

import iot.util.mq.IConsumer;
import iot.util.mq.IFactory;
import iot.util.mq.Message;
import iot.util.mq.MessageListener;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * File Name             :  RocketmqConsumerTest
 * Author                :  sylar
 * Create Date           :  2018/4/12
 * Description           :
 * Reviewed By           :
 * Reviewed On           :
 * Version History       :
 * Modified By           :
 * Modified Date         :
 * Comments              :
 * CopyRight             : COPYRIGHT(c) me.iot.com   All Rights Reserved
 * *******************************************************************************************
 */
public class RocketmqConsumerTest {

    IFactory factory;
    IConsumer consumer;

    String brokers = "127.0.0.1:9876";
    String topic = "TopicA";

    @Before
    public void setUp() throws Exception {
        factory = new RocketmqFactory();
        consumer = factory.createConsumer(brokers, "ConsumerGroup_1", "ConsumerClient_1");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void subscribe() throws Exception {
        consumer.subscribe(topic, null, new MessageListener() {
            @Override
            public void onSuccess(Message message) {

                if (message.getExt() instanceof MessageExt) {
                    MessageExt ext = (MessageExt) message.getExt();
                    System.out.println("msgId:" + ext.getMsgId());
                }

                System.out.println(String.format("[TOPIC]:%s [MESSAGE]:%s", message.getTopic(), message.getContent()));
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        Thread.sleep(1000 * 60 * 5);
        System.out.println("test over");
    }

    @Test
    public void unsubscribe() throws Exception {
        consumer.unsubscribe();
    }

}
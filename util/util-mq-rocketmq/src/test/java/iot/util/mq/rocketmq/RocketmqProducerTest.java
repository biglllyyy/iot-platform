package iot.util.mq.rocketmq;

import iot.util.mq.IFactory;
import iot.util.mq.IProducer;
import iot.util.mq.Message;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * File Name             :  RocketmqProducerTest
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
public class RocketmqProducerTest {

    IFactory factory;
    IProducer producer;

    String brokers = "localhost:9876";
    String topic = "TopicA";

    @Before
    public void setUp() throws Exception {
        factory = new RocketmqFactory();
        producer = factory.createProducer(brokers, "ProducerGroup_1", "ProducerClient_1");

        producer.start();
    }

    @After
    public void tearDown() throws Exception {
        producer.stop();
    }

    @Test
    public void send() throws Exception {
        Message msg = new Message(topic, "this is a test message");
        SendResult result = (SendResult) producer.send(msg);
        System.out.println("send success. msgId:" + result.getMsgId());
    }

}
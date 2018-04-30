package iot.util.mq.ons.http;

import iot.util.mq.IFactory;
import iot.util.mq.IProducer;
import iot.util.mq.Message;
import iot.util.mq.ons.OnsConst;
import iot.util.mq.ons.OnsServerConst;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * File Name             :  OnsHttpProducerTest
 * Author                :  sylar
 * Create Date           :  2018/4/14
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
public class OnsHttpProducerTest {
    IFactory factory;
    IProducer producer;
    OnsConst onsConst;

    @Before
    public void setUp() throws Exception {
        onsConst = OnsConst.getFirst();
        factory = new OnsHttpFactory(onsConst.getAccessKey(), onsConst.getSecretKey());
        producer = factory.createProducer(OnsServerConst.TCP_TEST, onsConst.getProducerId(), "ProducerClient_1");

        producer.start();
    }

    @After
    public void tearDown() throws Exception {
        producer.stop();
    }

    @Test
    public void send() throws Exception {
        Message msg = new Message(onsConst.getTopic(), "this is a test message");
        HttpResult result = (HttpResult) producer.send(msg);
        System.out.println("send success. statusCode:" + result.getStatusCode());
    }

}
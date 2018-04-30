package iot.util.mq.ons.tcp;

import iot.util.mq.*;
import iot.util.mq.ons.OnsConst;
import iot.util.mq.ons.OnsServerConst;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * File Name             :  OnsTcpConsumerTest
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
public class OnsTcpConsumerTest {

    IFactory factory;
    IConsumer consumer;
    OnsConst onsConst;

    @Before
    public void setUp() throws Exception {
        onsConst = OnsConst.getFirst();
        factory = new OnsTcpFactory(onsConst.getAccessKey(),onsConst.getSecretKey());
        consumer = factory.createConsumer(OnsServerConst.TCP_TEST, onsConst.getConsumerId(), "ConsumerClient_1");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void subscribe() throws Exception {
        consumer.subscribe(onsConst.getTopic(), null, new MessageListener() {
            @Override
            public void onSuccess(Message message) {

                if (message.getExt() instanceof com.aliyun.openservices.ons.api.Message) {
                    com.aliyun.openservices.ons.api.Message ext = (com.aliyun.openservices.ons.api.Message) message.getExt();
                    System.out.println("msgId:" + ext.getMsgID());
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
package iot.util.mq.starter;

import iot.util.misc.NetUtils;
import iot.util.mq.IConsumer;
import iot.util.mq.IFactory;
import iot.util.mq.IProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * File Name             :  MqAssistant
 * Author                :  sylar
 * Create Date           :  2018/4/18
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

@Component
public class MqAssistant {

    @Autowired
    private MqProperties mqProperties;

    @Autowired
    private IFactory factory;

    public IProducer createPrducer(String groupId) {
        return createPrducer(groupId, NetUtils.getHostMac());
    }

    public IProducer createPrducer(String groupId, String clientId) {
        return factory.createProducer(mqProperties.getBrokers(), groupId, clientId);
    }

    public IConsumer createConsumer(String groupId) {
        return createConsumer(groupId, NetUtils.getHostMac());
    }

    public IConsumer createConsumer(String groupId, String clientId) {
        return factory.createConsumer(mqProperties.getBrokers(), groupId, clientId);
    }
}

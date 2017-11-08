package me.iot.das.common.bean;

import me.iot.common.msg.IMsg;
import me.iot.common.usual.TopicConsts;
import me.iot.das.common.DasConfig;
import me.iot.util.mq.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @FileName             :  MqttConst
 * @Author                :  sylar
 * @CreateDate           :  2017/11/08
 * @Description           :  消息抛掷器: 将消息抛给队列
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @CopyRight             : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
@Component
public class MsgThrower {

    private static final Logger LOG = LoggerFactory.getLogger(MsgThrower.class);

    @Autowired
    DasConfig dasConfig;

    public void sendToQueue(IMsg msg) {
        try {
            String topic = TopicConsts.getTopicFromDasToDms();
            dasConfig.getProducer().send(Message.buildMessage(topic, msg));
        } catch (Exception e) {
            LOG.error("sendToQueue error:{}", e.getMessage());
            e.printStackTrace();
        }

    }
}

package me.iot.das.common.bean;

import com.alibaba.fastjson.JSON;
import me.iot.common.msg.IMsg;
import me.iot.common.usual.GroupConsts;
import me.iot.common.usual.TopicConsts;
import me.iot.das.common.DasConfig;
import me.iot.util.rocketmq.IProducer;
import me.iot.util.rocketmq.IProducerConfig;
import me.iot.util.rocketmq.msg.RocketMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author :  sylar
 * @FileName :  MsgThrower
 * @CreateDate :  2017/11/08
 * @Description :  消息抛掷器: 将消息抛给队列
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @CopyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
@Component
public class MsgThrower {

    private static final Logger LOG = LoggerFactory.getLogger(MsgThrower.class);

    @Autowired
    DasConfig dasConfig;

    public void sendToQueue(IMsg msg) {
        try {
            String topic = TopicConsts.DAS_TO_DMS;

            IProducer producer = dasConfig.getFactory().createProducer(new IProducerConfig() {
                @Override
                public String getProducerId() {
                    return GroupConsts.IOT_DMS_GROUP;
                }
            });

            RocketMsg rocketMsg = new RocketMsg(topic);
            rocketMsg.setContent(JSON.toJSONString(msg));
            producer.syncSend(rocketMsg);
        } catch (Exception e) {
            LOG.error("sendToQueue error:{}", e.getMessage());
            e.printStackTrace();
        }

    }
}

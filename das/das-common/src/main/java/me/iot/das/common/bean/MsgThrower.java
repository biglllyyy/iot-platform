package me.iot.das.common.bean;

import com.alibaba.fastjson.JSON;
import me.iot.common.msg.IMsg;
import me.iot.common.msg.MsgType;
import me.iot.common.pojo.CacheMsgWrap;
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

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


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

    private IProducer producer;

    @PostConstruct
    public void init() {
        producer = dasConfig.getFactory().createProducer(new IProducerConfig() {
            @Override
            public String getProducerId() {
                return String.join("-", GroupConsts.IOT_DAS_TO_DMS_GROUP, this.getClass().getCanonicalName());
            }
        });
    }

    public void sendToQueue(IMsg msg) {
        try {
            String topic = TopicConsts.DAS_TO_DMS;

            RocketMsg rocketMsg = new RocketMsg(topic);

            CacheMsgWrap wrap = new CacheMsgWrap(msg);
            rocketMsg.setContent(JSON.toJSONString(wrap));
            
            producer.syncSend(rocketMsg);
        } catch (Exception e) {
            LOG.error("sendToQueue error:{}", e.getMessage());
            e.printStackTrace();
        }

    }

    @PreDestroy
    private void destroy() {
        if (producer != null) {
            producer.shutdown();
        }
    }

}

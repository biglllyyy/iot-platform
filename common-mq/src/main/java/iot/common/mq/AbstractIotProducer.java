package iot.common.mq;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import iot.common.msg.IMsg;
import iot.common.pojo.CacheMsgWrap;
import iot.util.mq.IProducer;
import iot.util.mq.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * File Name             :  AbstractIotProducer
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
public abstract class AbstractIotProducer {
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * 生产者待发送消息的主题
     *
     * @return 消息主题
     */
    protected abstract String getTopic();

    /**
     * 生产者所在组
     *
     * @return 生产者组
     */
    protected abstract String getProducerGroupId();


    @Autowired
    protected MqAssistant mqAssistant;

    protected IProducer producer;

    @PostConstruct
    protected void onInit() {
        try {
            producer = mqAssistant.createPrducer(getProducerGroupId());
            producer.start();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @PreDestroy
    protected void onDestory() {
        producer.stop();
        producer = null;
    }

    public void publish(IMsg msg) {
        publish(msg, null);
    }

    public void publish(IMsg msg, String tagId) {
        if (msg == null) {
            return;
        }

        CacheMsgWrap wrap = new CacheMsgWrap(msg);
        publish(JSON.toJSONString(wrap), tagId);
    }

    public void publish(String messageContent, String tagId) {
        if (Strings.isNullOrEmpty(messageContent)) {
            return;
        }

        try {
            Message message = new Message(getTopic(), messageContent);
            message.setTags(tagId);
            producer.send(message);
        } catch (Exception e) {
            LOG.error("sendToQueue error:{}", e.getMessage());
            e.printStackTrace();
        }
    }
}

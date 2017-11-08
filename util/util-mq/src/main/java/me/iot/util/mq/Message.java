package me.iot.util.mq;

import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;

/**
 * @FileName             :  MqttConst
 * @Author                :  sylar
 * @CreateDate           :  2017/11/08
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @CopyRight             : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public class Message {

    public static <POJO> Message buildMessage(String topic, POJO pojo) {
        Preconditions.checkNotNull(pojo);
        return new Message(topic, JSON.toJSONString(pojo));
    }

    private String topic;
    private String content;
    private Object tag;

    public Message(String topic) {
        this.topic = topic;
    }

    public Message(String topic, String content) {
        this.topic = topic;
        this.content = content;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }


}

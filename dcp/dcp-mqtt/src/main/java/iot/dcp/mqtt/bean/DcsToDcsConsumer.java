package iot.dcp.mqtt.bean;

import com.alibaba.fastjson.JSON;
import iot.common.consts.MqConsts;
import iot.common.mq.AbstractIotConsumer;
import iot.dcp.mqtt.protocol.message.PublishMessage;
import iot.util.mq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * File Name             :  DcsToDcsConsumer
 * Author                :  sylar
 * Create Date           :  2018/4/21
 * Description           :  订阅其它MQTT服务器发过来的消息
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
public class DcsToDcsConsumer extends AbstractIotConsumer {

    @Autowired
    MqttMsgSender mqttMsgSender;

    @Override
    protected String getTopic() {
        return MqConsts.TOPIC_DCS_TO_DCS;
    }

    @Override
    protected String getConsumerGroupId() {
        return MqConsts.CID_DCS_TO_DCS_GROUP;
    }

    @Override
    protected void onConsume(Message message) {
        PublishMessage publishMessage = JSON.parseObject(message.getContent(), PublishMessage.class);
        if (publishMessage != null) {
            mqttMsgSender.send(publishMessage);
        }
    }
}

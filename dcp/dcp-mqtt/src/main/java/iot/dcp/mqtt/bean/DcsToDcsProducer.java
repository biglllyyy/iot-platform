package iot.dcp.mqtt.bean;

import com.alibaba.fastjson.JSON;
import iot.common.consts.MqConsts;
import iot.common.mq.AbstractIotProducer;
import iot.dcp.mqtt.protocol.message.PublishMessage;
import org.springframework.stereotype.Component;

/**
 * File Name             :  DcsToDcsProducer
 * Author                :  sylar
 * Create Date           :  2018/4/21
 * Description           :  发送消息给其它MQTT服务器
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
public class DcsToDcsProducer extends AbstractIotProducer {
    @Override
    protected String getTopic() {
        return MqConsts.TOPIC_DCS_TO_DCS;
    }

    @Override
    protected String getProducerGroupId() {
        return MqConsts.PID_DCS_TO_DCS_GROUP;
    }

    public void publish(PublishMessage publishMessage, String tagId) {
        String json = JSON.toJSONString(publishMessage);
        publish(json, tagId);
    }
}

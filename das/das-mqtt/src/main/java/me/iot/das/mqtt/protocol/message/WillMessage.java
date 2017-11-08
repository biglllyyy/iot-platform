package me.iot.das.mqtt.protocol.message;

import java.nio.ByteBuffer;

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
public class WillMessage extends PublishMessage {

    public WillMessage(String willTopic, ByteBuffer willMessage, boolean willRetain, QOSType willQos) {
        super();
        setTopicName(willTopic);
        setPayload(willMessage);
        setRetainFlag(willRetain);
        setQos(willQos);
    }

}

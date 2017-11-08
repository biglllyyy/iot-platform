package me.iot.das.mqtt.util;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import me.iot.common.pojo.DeviceGuid;
import me.iot.das.mqtt.pojo.MqttPacketWrap;
import me.iot.das.mqtt.protocol.message.AbstractMessage;
import me.iot.das.mqtt.protocol.message.PublishMessage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @FileName             :  MqttUtil
 * @@Author                :  sylar
 * @CreateDate           :  2017/11/08
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @@CopyRight             : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public class MqttUtil {

    public static PublishMessage createPublishMessage(MqttPacketWrap mqttPacketWrap) {
        return createPublishMessage(mqttPacketWrap, AbstractMessage.QOSType.MOST_ONE);
    }

    public static PublishMessage createPublishMessage(MqttPacketWrap mqttPacketWrap, AbstractMessage.QOSType qosType) {
        String sourceGuid = mqttPacketWrap.getSourceGuid().getGuid();
        String targetGuid = mqttPacketWrap.getTargetGuid().getGuid();

        Preconditions.checkNotNull(sourceGuid, "sourceGuid is null");
        Preconditions.checkState(sourceGuid.length() == DeviceGuid.GUID_LENGTH, "sourceGuid length error");

        byte[] bytes = mqttPacketWrap.getPayload().array();

        int count = DeviceGuid.GUID_LENGTH + 1 + bytes.length;
        ByteBuffer payload = ByteBuffer.allocate(count).order(ByteOrder.LITTLE_ENDIAN);
        payload.put(sourceGuid.getBytes());
        payload.put((byte) mqttPacketWrap.getCmdCode());
        payload.put(bytes);
        payload.flip();

        String topicName = Strings.isNullOrEmpty(targetGuid)
                ? TopicUtil.getBroadcastTopic(sourceGuid)
                : TopicUtil.getUnicastTopic(targetGuid);

        PublishMessage publishMessage = new PublishMessage();
        publishMessage.setTopicName(topicName);
        publishMessage.setPayload(payload);
        publishMessage.setQos(qosType);
        return publishMessage;
    }

}

package me.iot.das.mqtt;

import me.iot.common.usual.DasCacheKeys;

/**
 * Created by sylar on 16/5/29.
 */
public class MqttCacheKeys extends DasCacheKeys {

    public static String getCcsKeyForTopicByNode(String nodeId) {
        return getDasKey("topicByNode", nodeId);
    }

    public static String getCcsKeyForNodesByTopic(String topic) {
        return getDasKey("nodesByTopic", topic);
    }
}

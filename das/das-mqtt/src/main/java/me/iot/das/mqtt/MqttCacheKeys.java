package me.iot.das.mqtt;

import me.iot.common.usual.DasCacheKeys;

/**
 * @FileName             :  MqttCacheKeys
 * @@Author               :  sylar
 * @CreateDate           :  2017/11/08
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @@CopyRight            : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public class MqttCacheKeys extends DasCacheKeys {

    public static String getCcsKeyForTopicByNode(String nodeId) {
        return getDasKey("topicByNode", nodeId);
    }

    public static String getCcsKeyForNodesByTopic(String topic) {
        return getDasKey("nodesByTopic", topic);
    }
}

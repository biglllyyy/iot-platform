package iot.dcp.mqtt;

import iot.common.consts.CacheKeys;

/**
 * @author :  sylar
 * @FileName :  MqttCacheKeys
 * @CreateDate :  2017/11/08
 * @Description :
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @@CopyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public class MqttCacheKeys extends CacheKeys {

//    public static String getCcsKeyForTopicByNode(String nodeId) {
//        return generateKeyBySyscode("dcs", "mqttTopicByNode", nodeId);
//    }

    public static String getKey_nodesByMqttTopic(String topic) {
        return generateKeyBySyscode("dcs", "nodesByMqttTopic", topic);
    }
}

package me.iot.util.rocketmq;

import me.iot.util.rocketmq.ons.http.OnsHttpFactory;
import me.iot.util.rocketmq.ons.mqtt.OnsMqttFactory;
import me.iot.util.rocketmq.ons.tcp.OnsTcpFactory;
import me.iot.util.rocketmq.own.OwnFactory;

/**
 * Created by sylar on 2017/1/6.
 */
public class RocketMQUtil {

    public static IFactory createOnsTcpFactory(String accessKey, String secretKey, String serverEndpoint) {
        return new OnsTcpFactory(accessKey, secretKey, serverEndpoint);
    }

    public static IFactory createOnsMqttFactory(String accessKey, String secretKey, String serverEndpoint, String clientId) {
        return new OnsMqttFactory(accessKey, secretKey, serverEndpoint, clientId);
    }

    public static IFactory createOnsHttpFactory(String accessKey, String secretKey, String serverEndpoint) {
        return new OnsHttpFactory(accessKey, secretKey, serverEndpoint);
    }

    public static IFactory createOwnFactory(String serverEndpoint) {
        return new OwnFactory(serverEndpoint);
    }

}

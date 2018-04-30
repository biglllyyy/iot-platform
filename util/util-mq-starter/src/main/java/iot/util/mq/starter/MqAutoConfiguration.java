package iot.util.mq.starter;

import iot.util.mq.IFactory;
import iot.util.mq.kafka.KafkaFactory;
import iot.util.mq.ons.http.OnsHttpFactory;
import iot.util.mq.ons.mqtt.OnsMqttFactory;
import iot.util.mq.ons.tcp.OnsTcpFactory;
import iot.util.mq.rocketmq.RocketmqFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * File Name             :  MqAutoConfiguration
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
@Configuration
@EnableConfigurationProperties(MqProperties.class)
public class MqAutoConfiguration {

    @Autowired
    private MqProperties mqProperties;

    @Bean
    @ConditionalOnMissingBean(IFactory.class)
    public IFactory getFactory() {
        switch (mqProperties.getProvider()) {
            case MqProperties.PROVIDER_ROCKETMQ:
                return new RocketmqFactory();
            case MqProperties.PROVIDER_KAFKA:
                return new KafkaFactory();
            case MqProperties.PROVIDER_ONS_TCP:
                return new OnsTcpFactory(mqProperties.getAccessKey(), mqProperties.getSecretKey());
            case MqProperties.PROVIDER_ONS_HTTP:
                return new OnsHttpFactory(mqProperties.getAccessKey(), mqProperties.getSecretKey());
            case MqProperties.PROVIDER_ONS_MQTT:
                return new OnsMqttFactory(mqProperties.getAccessKey(), mqProperties.getSecretKey());
            default:
                return new RocketmqFactory();
        }
    }
}

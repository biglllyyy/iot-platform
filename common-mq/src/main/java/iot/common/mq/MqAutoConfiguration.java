package iot.common.mq;

import iot.util.mq.IFactory;
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
        return new RocketmqFactory();
    }
}

package iot.common.mq;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

/**
 * File Name             :  MqProperties
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
@ConfigurationProperties(prefix = MqProperties.PREFIX_MQ)
public class MqProperties extends Properties {

    final public static String PREFIX_MQ = "iot.mq";
    final public static String PREFIX_BROKERS = PREFIX_MQ + ".brokers";

    private String brokers = "localhost:9876";

    public String getBrokers() {
        return brokers;
    }

    public void setBrokers(String brokers) {
        this.brokers = brokers;
    }
}


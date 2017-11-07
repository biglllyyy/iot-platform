package me.iot.util.kafka.producer;

import me.iot.util.kafka.AbsServiceConfig;

/**
 * Created by sylar on 2017/3/9.
 */
public class SimpleProducerConfig extends AbsServiceConfig implements IProducerConfig {

    public SimpleProducerConfig(String bootstrapServers, String clientId) {
        super(bootstrapServers, clientId);
    }
}

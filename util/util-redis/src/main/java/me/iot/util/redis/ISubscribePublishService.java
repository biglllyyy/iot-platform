package me.iot.util.redis;

import java.util.List;

public interface ISubscribePublishService {
    void publishMessage(String topic, Object msg);

    void subscribeMessage(AbstractMessageListener messageListener, List<String> topics);

    void unsubscribeMessage(AbstractMessageListener messageListener, List<String> topics);
}

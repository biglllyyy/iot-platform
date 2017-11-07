package me.iot.util.mq;

import java.util.List;
import java.util.Properties;

public interface IConsumer {

    /**
     * 设置消费者参数配置
     * @param properties  参数配置
     */
    void setProperties(Properties properties);

    /**
     * 订阅
     *
     * @param topics          主题列表
     * @param messageListener 消息监听器
     */
    void subscribe(List<String> topics, MessageListener messageListener);


    /**
     * 取消订阅
     */
    void unsubscribe();
}

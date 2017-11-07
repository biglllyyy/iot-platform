package me.iot.util.mq;

import java.util.Properties;

public interface IProducer {

    /**
     * 设置生产者参数配置
     * @param properties  参数配置
     */
    void setProperties(Properties properties);

    /**
     * 发送消息前需要衔启动
     *
     * @throws Exception
     */
    void start() throws Exception;

    /**
     * 发送消息
     *
     * @param message 待发送的消息
     */
    void send(Message message) throws Exception;

    /**
     * 不再需要发生消息时可以停用，以节省资源
     */
    void stop();
}

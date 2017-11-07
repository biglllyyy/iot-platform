package me.iot.util.mq;

public interface IProducer {

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
     * 不再需要发消息时可以停用，以节省资源
     */
    void stop();
}

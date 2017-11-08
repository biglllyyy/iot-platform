package me.iot.util.mq;

/**
 * @FileName             :  MqttConst
 * @Author                :  sylar
 * @CreateDate           :  2017/11/08
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @CopyRight             : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
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

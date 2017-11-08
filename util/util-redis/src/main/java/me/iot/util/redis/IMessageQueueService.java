package me.iot.util.redis;
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
public interface IMessageQueueService {

    boolean containsQueue(String queueName);

    long getRemainSize(String queueName);

    <Msg> void sendMessage(String queueName, Msg msg);

    <Msg> Msg receiveMessage(String queueName, Class<Msg> clazz);

}

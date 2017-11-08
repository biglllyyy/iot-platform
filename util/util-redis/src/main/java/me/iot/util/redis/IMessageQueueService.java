package me.iot.util.redis;

/**
 * @author :  sylar
 * @FileName :  IMessageQueueService
 * @CreateDate :  2017/11/08
 * @Description :
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @CopyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public interface IMessageQueueService {

    /**
     * @param queueName
     * @return
     */
    boolean containsQueue(String queueName);

    /**
     * @param queueName
     * @return
     */
    long getRemainSize(String queueName);

    /**
     * @param queueName
     * @param msg
     * @param <Msg>
     */
    <Msg> void sendMessage(String queueName, Msg msg);

    /**
     * @param queueName
     * @param clazz
     * @param <Msg>
     * @return
     */
    <Msg> Msg receiveMessage(String queueName, Class<Msg> clazz);

}

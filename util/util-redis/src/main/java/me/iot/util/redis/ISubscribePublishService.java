package me.iot.util.redis;

import java.util.List;

/**
 * @author :  sylar
 * @FileName :  ISubscribePublishService
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
public interface ISubscribePublishService {
    /**
     * @param topic
     * @param msg
     */
    void publishMessage(String topic, Object msg);

    /**
     * @param messageListener
     * @param topics
     */
    void subscribeMessage(AbstractMessageListener messageListener, List<String> topics);

    /**
     * @param messageListener
     * @param topics
     */
    void unsubscribeMessage(AbstractMessageListener messageListener, List<String> topics);
}

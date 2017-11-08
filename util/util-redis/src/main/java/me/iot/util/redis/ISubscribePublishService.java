package me.iot.util.redis;

import java.util.List;
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
public interface ISubscribePublishService {
    void publishMessage(String topic, Object msg);

    void subscribeMessage(AbstractMessageListener messageListener, List<String> topics);

    void unsubscribeMessage(AbstractMessageListener messageListener, List<String> topics);
}

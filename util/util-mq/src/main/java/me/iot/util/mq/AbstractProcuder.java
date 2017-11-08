package me.iot.util.mq;

import com.google.common.base.Preconditions;

import java.util.Properties;

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
public abstract class AbstractProcuder extends AbstractClient implements IProducer {

    @Override
    public void start() throws Exception {
        stop();
    }

    @Override
    public void send(Message message) throws Exception {
        Preconditions.checkNotNull(message, "message is null");
        Preconditions.checkNotNull(message.getTopic(), "message topic is null");
        Preconditions.checkNotNull(message.getContent(), "message content is null");
    }

    @Override
    public void stop() {

    }
}

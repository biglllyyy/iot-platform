package me.iot.util.mq;

import com.google.common.base.Preconditions;

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
public abstract class AbstractConsumer extends AbstractClient implements IConsumer {

    protected boolean isBroadcasting = false;

    @Override
    public void subscribe(List<String> topics, MessageListener messageListener) {
        Preconditions.checkNotNull(topics, "topics is null");
        Preconditions.checkNotNull(messageListener, "messageListener is null");

        Preconditions.checkState(topics.size() > 0, "topics is empty");

        unsubscribe();
    }

    @Override
    public void setBroadcasting(boolean isBroadcasting) {
        this.isBroadcasting = isBroadcasting;
    }
}

package me.iot.util.disruptor;

import com.lmax.disruptor.EventFactory;
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
public class ValueEventFactory implements EventFactory<ValueEvent> {
    @Override
    public ValueEvent newInstance() {
        return new ValueEvent();
    }
}

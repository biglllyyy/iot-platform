package me.iot.util.disruptor;
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
public final class ValueEvent {

    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

package me.iot.common.msg;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by sylar on 16/5/29.
 */

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
public interface IMsg extends Serializable {

    MsgType getMsgType();

    String getMsgCode();

    String getSourceDeviceType();

    String getSourceDeviceId();

    String getTargetDeviceType();

    String getTargetDeviceId();

    long getOccurTime();

    Object getTag();

    Map<String, Object> getParams();

    <T> T get(String paramKey);
}

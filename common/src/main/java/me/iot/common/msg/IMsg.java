package me.iot.common.msg;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by sylar on 16/5/29.
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

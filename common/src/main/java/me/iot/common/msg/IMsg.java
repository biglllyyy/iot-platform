package me.iot.common.msg;

import java.io.Serializable;
import java.util.Map;

/**
 * @author :  sylar
 * @FileName :  IMsg
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
public interface IMsg extends Serializable {

    /**
     * @return
     */
    MsgType getMsgType();

    /**
     * @return
     */
    String getMsgCode();

    /**
     * @return
     */
    String getSourceDeviceType();

    /**
     * @return
     */
    String getSourceDeviceId();

    /**
     * @return
     */
    String getTargetDeviceType();

    /**
     * @return
     */
    String getTargetDeviceId();

    /**
     * @return
     */
    long getOccurTime();

    /**
     * @return
     */
    Object getTag();

    /**
     * @return
     */
    Map<String, Object> getParams();

    /**
     * @param paramKey
     * @param <T>
     * @return
     */
    <T> T get(String paramKey);
}

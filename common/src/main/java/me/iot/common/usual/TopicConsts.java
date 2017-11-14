package me.iot.common.usual;

/**
 * @author :  sylar
 * @FileName :  TopicConsts
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
public interface TopicConsts {

    /**
     * 上行设备消息主题，从DAS到DMS
     */
    String DAS_TO_DMS = "IOT-DasToDms";

    /**
     * 下行设备消息主题，从DMS到DAS
     */
    String DMS_TO_DAS = "IOT-DmsToDas";

    /**
     * 下行设备消息主题，从DMS到AP
     */
    String DMS_TO_APS = "IOT-DmsToAps";
}

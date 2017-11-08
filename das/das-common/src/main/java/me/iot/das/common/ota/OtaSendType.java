package me.iot.das.common.ota;

/**
 * @author :  sylar
 * @FileName :  MqttConst
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
public enum OtaSendType {

    /**
     * 每包回应后才发送下一包
     */
    EachPacketResponse,

    /**
     * 发包时不回应
     */
    LastPacketResponse,

}

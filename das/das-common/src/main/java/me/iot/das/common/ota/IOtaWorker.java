package me.iot.das.common.ota;

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
public interface IOtaWorker {

    String getDeviceType();

    OtaSendType getSendType();

    int getMaxFrameLength();

    void startOtaTask(String deviceId, byte[] otaData);
}

package me.iot.das.common.ota;

/**
 * Created by sylar on 16/6/1.
 */
public interface IOtaWorker {

    String getDeviceType();

    OtaSendType getSendType();

    int getMaxFrameLength();

    void startOtaTask(String deviceId, byte[] otaData);
}

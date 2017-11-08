package me.iot.das.common.event;

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
public class OtaEachPacketResponseEvent extends OtaEvent {
    private String deviceId;
    private int currentPacketIndex;

    public OtaEachPacketResponseEvent(Object source, String deviceId, int currentPacketIndex) {
        super(source);
        this.deviceId = deviceId;
        this.currentPacketIndex = currentPacketIndex;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getCurrentPacketIndex() {
        return currentPacketIndex;
    }

    public void setCurrentPacketIndex(int currentPacketIndex) {
        this.currentPacketIndex = currentPacketIndex;
    }
}

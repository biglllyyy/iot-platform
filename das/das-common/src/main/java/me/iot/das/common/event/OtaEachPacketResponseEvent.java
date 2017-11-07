package me.iot.das.common.event;

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

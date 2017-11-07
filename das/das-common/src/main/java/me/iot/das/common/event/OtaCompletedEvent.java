package me.iot.das.common.event;

public class OtaCompletedEvent extends OtaEvent {
    private String deviceId;

    public OtaCompletedEvent(Object source, String deviceId) {
        super(source);
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}

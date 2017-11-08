package me.iot.common.msg;

/**
 * 设备事件消息
 */
public class DeviceEventMsg extends AbstractDeviceMsg {
    private String eventCode;
    private String eventDescription;

    @Override
    public MsgType getMsgType() {
        return MsgType.DeviceEvent;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}

package me.iot.das.common.event;

import me.iot.common.msg.DeviceOtaMsg;

public class OtaNewTaskEvent extends OtaEvent {
    private DeviceOtaMsg msg;

    public OtaNewTaskEvent(Object source, DeviceOtaMsg msg) {
        super(source);
        this.msg = msg;
    }

    public DeviceOtaMsg getMsg() {
        return msg;
    }

    public void setMsg(DeviceOtaMsg msg) {
        this.msg = msg;
    }
}

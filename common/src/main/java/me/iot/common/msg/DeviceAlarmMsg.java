package me.iot.common.msg;

/**
 * 设备报警消息
 */
public class DeviceAlarmMsg extends AbstractDeviceMsg {
    private String alarmCode;
    private String alarmDescription;

    @Override
    public MsgType getMsgType() {
        return MsgType.DeviceAlarm;
    }

    public String getAlarmCode() {
        return alarmCode;
    }

    public void setAlarmCode(String alarmCode) {
        this.alarmCode = alarmCode;
    }

    public String getAlarmDescription() {
        return alarmDescription;
    }

    public void setAlarmDescription(String alarmDescription) {
        this.alarmDescription = alarmDescription;
    }
}

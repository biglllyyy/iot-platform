package me.iot.common.msg;

import java.io.Serializable;

/**
 * Created by sylar on 16/5/25.
 */
public enum MsgType implements Serializable {

    Unknown(-1),
    Undefine(0),
    DasConnection(1),
    DeviceConnection(2),
    DeviceInfo(11),
    DeviceAlarm(12),
    DeviceEvent(13),
    DeviceLog(14),
    DeviceData(15),
    DeviceOta(16);

    private int value;

    MsgType(int value) {
        this.value = value;
    }

    public static MsgType valueOf(int value) {
        switch (value) {
            case 0:
                return Undefine;
            case 1:
                return DasConnection;
            case 2:
                return DeviceConnection;
            case 11:
                return DeviceInfo;
            case 12:
                return DeviceAlarm;
            case 13:
                return DeviceEvent;
            case 14:
                return DeviceLog;
            case 15:
                return DeviceData;
            case 16:
                return DeviceOta;
            default:
                return Unknown;
        }
    }

    public int getValue() {
        return value;
    }


}

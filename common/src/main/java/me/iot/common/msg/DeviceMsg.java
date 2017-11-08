package me.iot.common.msg;

import me.iot.common.pojo.DeviceGuid;

/**
 * 普通设备消息
 */
public class DeviceMsg extends AbstractDeviceMsg {

    public static DeviceMsg newMsgFromCloud(String msgCode, String targetDeviceType, String targetDeviceId) {
        DeviceMsg msg = newMsgFromCloud(msgCode);
        msg.setTargetDevice(targetDeviceType, targetDeviceId);
        return msg;
    }

    public static DeviceMsg newMsgFromCloud(String msgCode) {
        DeviceMsg msg = newMsgFromCloud();
        msg.setMsgCode(msgCode);
        return msg;
    }

    public static DeviceMsg newMsgFromCloud() {
        DeviceMsg msg = new DeviceMsg();
        msg.setSourceDevice(DeviceGuid.getCloudType(), DeviceGuid.getCloudNum());
        return msg;
    }


    public static DeviceMsg newMsgToCloud(String msgCode, String sourceDeviceType, String sourceDeviceId) {
        DeviceMsg msg = newMsgToCloud(msgCode);
        msg.setSourceDevice(sourceDeviceType, sourceDeviceId);
        return msg;
    }

    public static DeviceMsg newMsgToCloud(String msgCode) {
        DeviceMsg msg = newMsgToCloud();
        msg.setMsgCode(msgCode);
        return msg;
    }

    public static DeviceMsg newMsgToCloud() {
        DeviceMsg msg = new DeviceMsg();
        msg.setTargetDevice(DeviceGuid.getCloudType(), DeviceGuid.getCloudNum());
        return msg;
    }


    @Override
    public MsgType getMsgType() {
        return MsgType.Undefine;
    }
}

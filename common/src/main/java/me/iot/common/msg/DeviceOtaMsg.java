package me.iot.common.msg;

/**
 * 设备ota升级消息
 * 设备ota是由云端发起,并作分包处理后发给设备的
 */
public class DeviceOtaMsg extends AbsDeviceMsg {

    private byte[] otaData;


    @Override
    public MsgType getMsgType() {
        return MsgType.DeviceOta;
    }

    public byte[] getOtaData() {
        return otaData;
    }

    public void setOtaData(byte[] otaData) {
        this.otaData = otaData;
    }
}

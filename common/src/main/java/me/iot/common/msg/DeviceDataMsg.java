package me.iot.common.msg;

/**
 * 设备实时数据消息
 */
public class DeviceDataMsg extends AbstractDeviceMsg {

    private long timestamp = System.currentTimeMillis();

    @Override
    public MsgType getMsgType() {
        return MsgType.DeviceData;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
